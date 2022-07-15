package com.example.foodinfo.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.foodinfo.model.local.dao.RecipesDAO
import com.example.foodinfo.model.local.dao.SearchFilterDAO
import com.example.foodinfo.model.local.dao.SearchInputHistoryDAO
import com.example.foodinfo.model.local.dao.type_converter.CategoryFieldTypeConverter
import com.example.foodinfo.model.local.dao.type_converter.NutrientFieldTypeConverter
import com.example.foodinfo.model.local.dao.type_converter.RangeFieldTypeConverter
import com.example.foodinfo.model.local.entities.Recipe
import com.example.foodinfo.model.local.entities.SearchFilter
import com.example.foodinfo.model.local.entities.SearchInput
import com.example.foodinfo.model.local.entities.recipe_field.*
import com.example.foodinfo.utils.AssetProvider
import com.example.foodinfo.utils.AssetsKeyWords
import com.example.foodinfo.utils.JSONLoader
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Database(
    version = 1,
    entities = [
        Recipe::class,
        SearchFilter::class,
        SearchInput::class,
        CuisineType::class,
        HealthType::class,
        DietType::class,
        DishType::class,
        MealType::class,
        Ingredient::class,
        Nutrient::class,
    ]

)
@TypeConverters(
    RangeFieldTypeConverter::class,
    CategoryFieldTypeConverter::class,
    NutrientFieldTypeConverter::class,
)
abstract class DataBase : RoomDatabase() {
    abstract val recipesDAO: RecipesDAO
    abstract val searchFilterDAO: SearchFilterDAO
    abstract val searchInputHistoryDAO: SearchInputHistoryDAO

    companion object {
        private const val DB_NAME = "data_base"

        private lateinit var dataBase: DataBase

        fun getDatabase(
            context: Context,
            assetProvider: AssetProvider,
            scope: CoroutineScope
        ): DataBase {
            if (!::dataBase.isInitialized)
                dataBase = Room.databaseBuilder(
                    context.applicationContext,
                    DataBase::class.java,
                    DB_NAME
                ).allowMainThreadQueries()
                    .addCallback(Callback(assetProvider, scope))
                    .build()
            return dataBase
        }
    }

    class Callback(
        private val assetProvider: AssetProvider,
        private val scope: CoroutineScope
    ) :
        RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val jsonLoader = JSONLoader()
            val gson = GsonBuilder().create()

            val predefinedDB = jsonLoader.load(
                assetProvider.getAsset(AssetsKeyWords.PREDEFINED_RECIPES_100)
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

            val typeRecipes = object : TypeToken<List<Recipe>>() {}.type
            val typeNutrients = object : TypeToken<List<Nutrient>>() {}.type
            val typeIngredients = object : TypeToken<List<Ingredient>>() {}.type
            val typeDishTypes = object : TypeToken<List<DishType>>() {}.type
            val typeMealTypes = object : TypeToken<List<MealType>>() {}.type
            val typeDietTypes = object : TypeToken<List<DietType>>() {}.type
            val typeHealthType = object : TypeToken<List<HealthType>>() {}.type
            val typeCuisineTypes = object : TypeToken<List<CuisineType>>() {}.type

            scope.launch {
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
            }
        }
    }
}