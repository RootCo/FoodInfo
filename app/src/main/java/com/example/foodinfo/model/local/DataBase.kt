package com.example.foodinfo.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.foodinfo.model.local.dao.RecipesDAO
import com.example.foodinfo.model.local.dao.SearchFilterDAO
import com.example.foodinfo.model.local.dao.SearchHistoryDAO
import com.example.foodinfo.model.local.dao.type_converter.CategoryFieldTypeConverter
import com.example.foodinfo.model.local.dao.type_converter.NutrientFieldTypeConverter
import com.example.foodinfo.model.local.dao.type_converter.RangeFieldTypeConverter
import com.example.foodinfo.model.local.entities.Recipe
import com.example.foodinfo.model.local.entities.SearchFilter
import com.example.foodinfo.model.local.entities.SearchInput
import com.example.foodinfo.model.local.entities.recipe_field.*


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
    abstract val searchHistoryDAO: SearchHistoryDAO

    companion object {
        private const val DB_NAME = "data_base"

        private lateinit var dataBase: DataBase

        fun getDatabase(
            context: Context,
        ): DataBase {
            if (!::dataBase.isInitialized)
                dataBase = Room.databaseBuilder(
                    context.applicationContext,
                    DataBase::class.java,
                    DB_NAME
                ).allowMainThreadQueries().build()
            return dataBase
        }
    }
}