package com.example.foodinfo.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.foodinfo.model.local.dao.*
import com.example.foodinfo.model.local.entities.*
import com.example.foodinfo.model.local.entities.recipe_field.*


@Database(
    version = 1,
    entities = [
        RecipeEntity::class,
        SearchFilterEntity::class,
        SearchInputEntity::class,
        RecipeCuisineLabelEntity::class,
        RecipeHealthLabelEntity::class,
        RecipeDietLabelEntity::class,
        RecipeDishLabelEntity::class,
        RecipeMealLabelEntity::class,
        RecipeIngredientEntity::class,
        RecipeNutrientEntity::class,
        CategoryLabelEntity::class,
        NutrientEntity::class,
    ]
)
abstract class DataBase : RoomDatabase() {
    abstract val recipesDAO: RecipesDAO
    abstract val searchFilterDAO: SearchFilterDAO
    abstract val searchHistoryDAO: SearchHistoryDAO
    abstract val categoryLabelsDAO: CategoryLabelsDAO
    abstract val nutrientsDAO: NutrientsDAO

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