package com.example.foodinfo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.foodinfo.databinding.ActivityRecipesBinding
import com.example.foodinfo.model.local.entities.Recipe
import com.example.foodinfo.model.local.entities.SearchInput
import com.example.foodinfo.model.local.entities.recipe_field.*
import com.example.foodinfo.utils.AssetsKeyWords
import com.example.foodinfo.utils.JSONLoader
import com.example.foodinfo.utils.applicationComponent
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecipesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecipesBinding.inflate(layoutInflater)
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

        val predefinedDB = jsonLoader.load(
            applicationComponent.assetProvider.getAsset(AssetsKeyWords.PREDEFINED_DB_100)
        )
        val recipesString = predefinedDB.get(AssetsKeyWords.RECIPES).toString()
        val nutrientsString = predefinedDB.get(AssetsKeyWords.NUTRIENTS).toString()
        val ingredientsString =
            predefinedDB.get(AssetsKeyWords.INGREDIENTS).toString()
        val dishTypesString = predefinedDB.get(AssetsKeyWords.DISH_TYPES).toString()
        val mealTypesString = predefinedDB.get(AssetsKeyWords.MEAL_TYPES).toString()
        val dietTypesString = predefinedDB.get(AssetsKeyWords.DIET_TYPES).toString()
        val healthTypesString =
            predefinedDB.get(AssetsKeyWords.HEALTH_TYPES).toString()
        val cuisineTypesString =
            predefinedDB.get(AssetsKeyWords.CUISINE_TYPES).toString()
        val searchHistoryString =
            predefinedDB.get(AssetsKeyWords.SEARCH_HISTORY).toString()

        val typeRecipes = object : TypeToken<List<Recipe>>() {}.type
        val typeNutrients = object : TypeToken<List<Nutrient>>() {}.type
        val typeIngredients = object : TypeToken<List<Ingredient>>() {}.type
        val typeDishTypes = object : TypeToken<List<DishType>>() {}.type
        val typeMealTypes = object : TypeToken<List<MealType>>() {}.type
        val typeDietTypes = object : TypeToken<List<DietType>>() {}.type
        val typeHealthType = object : TypeToken<List<HealthType>>() {}.type
        val typeCuisineTypes = object : TypeToken<List<CuisineType>>() {}.type
        val typeSearchInput = object : TypeToken<List<SearchInput>>() {}.type

        dataBase.recipesDAO.addAll(
            gson.fromJson(recipesString, typeRecipes),
            gson.fromJson(nutrientsString, typeNutrients),
            gson.fromJson(ingredientsString, typeIngredients),
            gson.fromJson(dietTypesString, typeDietTypes),
            gson.fromJson(dishTypesString, typeDishTypes),
            gson.fromJson(mealTypesString, typeMealTypes),
            gson.fromJson(healthTypesString, typeHealthType),
            gson.fromJson(cuisineTypesString, typeCuisineTypes)
        )
        dataBase.searchHistoryDAO.addHistory(
            gson.fromJson(searchHistoryString, typeSearchInput)
        )
    }
}