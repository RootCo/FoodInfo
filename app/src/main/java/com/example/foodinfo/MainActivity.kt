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
                R.id.f_favorite,
                R.id.f_planner,
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
                object : TypeToken<List<NutrientRecipeEntity>>() {}.type
            ),
            gson.fromJson(
                dbRecipes.get(AssetsKeyWords.INGREDIENTS).toString(),
                object : TypeToken<List<RecipeIngredientEntity>>() {}.type
            ),
            gson.fromJson(
                dbRecipes.get(AssetsKeyWords.LABELS).toString(),
                object : TypeToken<List<LabelRecipeEntity>>() {}.type
            ),
            gson.fromJson(
                dbRecipes.get(AssetsKeyWords.FAVORITE_MARKS).toString(),
                object : TypeToken<List<FavoriteMarkEntity>>() {}.type
            )
        )
        dataBase.searchHistoryDAO.addHistory(
            gson.fromJson(
                dbRecipes.get(AssetsKeyWords.SEARCH_HISTORY).toString(),
                object : TypeToken<List<SearchInputEntity>>() {}.type
            )
        )


        val dbRecipeFieldsInfo = jsonLoader.load(
            appComponent.assetProvider.getAsset(AssetsKeyWords.DB_FIELDS_INFO)
        )
        dataBase.recipeFieldsInfoDao.addLabelsFields(
            gson.fromJson(
                dbRecipeFieldsInfo.get(AssetsKeyWords.LABELS).toString(),
                object : TypeToken<List<LabelFieldEntity>>() {}.type
            )
        )
        dataBase.recipeFieldsInfoDao.addCategoriesFields(
            gson.fromJson(
                dbRecipeFieldsInfo.get(AssetsKeyWords.CATEGORIES).toString(),
                object : TypeToken<List<CategoryFieldEntity>>() {}.type
            )
        )
        dataBase.recipeFieldsInfoDao.addBaseFields(
            gson.fromJson(
                dbRecipeFieldsInfo.get(AssetsKeyWords.BASE).toString(),
                object : TypeToken<List<BaseFieldEntity>>() {}.type
            )
        )
        dataBase.recipeFieldsInfoDao.addNutrientFields(
            gson.fromJson(
                dbRecipeFieldsInfo.get(AssetsKeyWords.NUTRIENTS).toString(),
                object : TypeToken<List<NutrientFieldEntity>>() {}.type
            )
        )


        val labels = dataBase.recipeFieldsInfoDao.getLabelFields().map { label ->
            LabelFilterEntity(
                filterName = SearchFilterEntity.DEFAULT_NAME,
                category = label.category,
                name = label.name,
                isSelected = false
            )
        }
        val nutrients = dataBase.recipeFieldsInfoDao.getNutrientFields().map { nutrient ->
            NutrientFilterEntity(
                filterName = SearchFilterEntity.DEFAULT_NAME,
                name = nutrient.name,
                minValue = nutrient.rangeMin,
                maxValue = nutrient.rangeMax
            )
        }
        val baseFields = dataBase.recipeFieldsInfoDao.getBaseFields().map { nutrient ->
            BaseFieldFilterEntity(
                filterName = SearchFilterEntity.DEFAULT_NAME,
                name = nutrient.name,
                minValue = nutrient.rangeMin,
                maxValue = nutrient.rangeMax
            )
        }
        dataBase.searchFilterDAO.createBlankFilter(labels, nutrients, baseFields)
    }
}