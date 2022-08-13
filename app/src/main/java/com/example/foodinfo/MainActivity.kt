package com.example.foodinfo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.foodinfo.databinding.ActivityMainBinding
import com.example.foodinfo.local.entity.*
import com.example.foodinfo.utils.AssetsKeyWords
import com.example.foodinfo.utils.JSONLoader
import com.example.foodinfo.utils.appComponent
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navigationHost =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_recipes) as NavHostFragment
        val navController = navigationHost.navController


        NavigationUI.setupWithNavController(binding.navView, navController)

        navController.addOnDestinationChangedListener { _, destination: NavDestination, _ ->
            when (destination.id) {
                R.id.f_home,
                R.id.f_explore,
                R.id.f_settings -> binding.navView.isVisible = true
                else            -> binding.navView.isVisible = false
            }
        }

        prepopulateDB()
    }

    private fun prepopulateDB() {
        val dataBase = appComponent.dataBase
        val jsonLoader = JSONLoader()
        val gson = GsonBuilder().create()

        dataBase.clearAllTables()

        val dbRecipes = jsonLoader.load(
            appComponent.assetProvider.getAsset(AssetsKeyWords.DB_RECIPES_100)
        )

        dataBase.recipesDAO.addAll(
            gson.fromJson(
                dbRecipes.get(AssetsKeyWords.RECIPES).toString(),
                object : TypeToken<List<RecipeEntity>>() {}.type
            ),
            gson.fromJson(
                dbRecipes.get(AssetsKeyWords.NUTRIENTS).toString(),
                object : TypeToken<List<RecipeNutrientEntity>>() {}.type
            ),
            gson.fromJson(
                dbRecipes.get(AssetsKeyWords.INGREDIENTS).toString(),
                object : TypeToken<List<RecipeIngredientEntity>>() {}.type
            ),
            gson.fromJson(
                dbRecipes.get(AssetsKeyWords.LABELS).toString(),
                object : TypeToken<List<RecipeLabelEntity>>() {}.type
            )
        )

        dataBase.searchHistoryDAO.addHistory(
            gson.fromJson(
                dbRecipes.get(AssetsKeyWords.SEARCH_HISTORY).toString(),
                object : TypeToken<List<SearchInputEntity>>() {}.type
            )
        )

        val dbCategory = jsonLoader.load(
            appComponent.assetProvider.getAsset(AssetsKeyWords.DB_CATEGORY_LOCAL)
        )


        val typeCategoryLabel = object : TypeToken<List<LabelEntity>>() {}.type

        dataBase.labelsDAO.addCategory(
            gson.fromJson
                (
                dbCategory.get(AssetsKeyWords.CATEGORY_MEAL).toString(),
                typeCategoryLabel
            )
        )
        dataBase.labelsDAO.addCategory(
            gson.fromJson(
                dbCategory.get(AssetsKeyWords.CATEGORY_DISH).toString(),
                typeCategoryLabel
            )
        )
        dataBase.labelsDAO.addCategory(
            gson.fromJson
                (
                dbCategory.get(AssetsKeyWords.CATEGORY_DIET).toString(),
                typeCategoryLabel
            )
        )
        dataBase.labelsDAO.addCategory(
            gson.fromJson(
                dbCategory.get(AssetsKeyWords.CATEGORY_HEALTH).toString(),
                typeCategoryLabel
            )
        )
        dataBase.labelsDAO.addCategory(
            gson.fromJson(
                dbCategory.get(AssetsKeyWords.CATEGORY_CUISINE).toString(),
                typeCategoryLabel
            )
        )

        val dbNutrients = jsonLoader.load(
            appComponent.assetProvider.getAsset(AssetsKeyWords.DB_NUTRIENT_LOCAL)
        )

        dataBase.nutrientsDAO.addAll(
            gson.fromJson(
                dbNutrients.get(AssetsKeyWords.CONTENT).toString(),
                object : TypeToken<List<NutrientEntity>>() {}.type
            )
        )
    }
}