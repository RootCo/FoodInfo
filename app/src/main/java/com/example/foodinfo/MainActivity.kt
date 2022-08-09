package com.example.foodinfo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.foodinfo.databinding.ActivityMainBinding
import com.example.foodinfo.model.local.entities.LabelEntity
import com.example.foodinfo.model.local.entities.NutrientEntity
import com.example.foodinfo.model.local.entities.recipe.RecipeEntity
import com.example.foodinfo.model.local.entities.SearchInputEntity
import com.example.foodinfo.model.local.entities.RecipeIngredientEntity
import com.example.foodinfo.model.local.entities.RecipeLabelEntity
import com.example.foodinfo.model.local.entities.RecipeNutrientEntity
import com.example.foodinfo.utils.AssetsKeyWords
import com.example.foodinfo.utils.JSONLoader
import com.example.foodinfo.utils.applicationComponent
import com.example.foodinfo.utils.queryExample
import com.google.android.material.bottomnavigation.BottomNavigationView
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

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.nav_view)
        NavigationUI.setupWithNavController(
            bottomNavigationView, navController
        )

        // не долго думая сделал так как ты и рассказывал
        navController.addOnDestinationChangedListener { _, destination: NavDestination, _ ->
            when (destination.id) {
                R.id.f_home,
                R.id.f_explore,
                R.id.f_settings -> bottomNavigationView.isVisible = true
                else            -> bottomNavigationView.isVisible = false
            }
        }

        prepopulateDB()
    }

    private fun prepopulateDB() {
        val dataBase = applicationComponent.dataBase
        val jsonLoader = JSONLoader()
        val gson = GsonBuilder().create()

        dataBase.clearAllTables()

        val dbRecipes = jsonLoader.load(
            applicationComponent.assetProvider.getAsset(AssetsKeyWords.DB_RECIPES_100)
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
            applicationComponent.assetProvider.getAsset(AssetsKeyWords.DB_CATEGORY)
        )

        val mealString = dbCategory.get(AssetsKeyWords.CATEGORY_MEAL).toString()
        val dishString = dbCategory.get(AssetsKeyWords.CATEGORY_DISH).toString()
        val dietString = dbCategory.get(AssetsKeyWords.CATEGORY_DIET).toString()
        val healthString = dbCategory.get(AssetsKeyWords.CATEGORY_HEALTH).toString()
        val cuisineString = dbCategory.get(AssetsKeyWords.CATEGORY_CUISINE).toString()

        val typeCategoryLabel = object : TypeToken<List<LabelEntity>>() {}.type

        dataBase.labelsDAO.addCategory(
            gson.fromJson(mealString, typeCategoryLabel)
        )
        dataBase.labelsDAO.addCategory(
            gson.fromJson(dishString, typeCategoryLabel)
        )
        dataBase.labelsDAO.addCategory(
            gson.fromJson(dietString, typeCategoryLabel)
        )
        dataBase.labelsDAO.addCategory(
            gson.fromJson(healthString, typeCategoryLabel)
        )
        dataBase.labelsDAO.addCategory(
            gson.fromJson(cuisineString, typeCategoryLabel)
        )

        val dbNutrients = jsonLoader.load(
            applicationComponent.assetProvider.getAsset(AssetsKeyWords.DB_NUTRIENT)
        )

        dataBase.nutrientsDAO.addAll(
            gson.fromJson(
                dbNutrients.get(AssetsKeyWords.CONTENT).toString(),
                object : TypeToken<List<NutrientEntity>>() {}.type
            )
        )
    }
}