package com.example.foodinfo.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.foodinfo.local.dao.RecipeFieldsInfoDao
import com.example.foodinfo.local.dao.RecipesDAO
import com.example.foodinfo.local.dao.SearchFilterDAO
import com.example.foodinfo.local.dao.SearchHistoryDAO
import com.example.foodinfo.local.entity.*


@Database(
    version = 1,
    entities = [
        RecipeEntity::class,
        SearchFilterEntity::class,
        SearchInputEntity::class,
        RecipeIngredientEntity::class,
        NutrientRecipeEntity::class,
        LabelRecipeEntity::class,
        LabelFieldEntity::class,
        CategoryFieldEntity::class,
        FavoriteMarkEntity::class,
        LabelFilterEntity::class,
        NutrientFilterEntity::class,
        NutrientFieldEntity::class,
        BaseFieldEntity::class,
        BaseFieldFilterEntity::class,
    ]
)
abstract class DataBase : RoomDatabase() {
    abstract val recipesDAO: RecipesDAO
    abstract val searchFilterDAO: SearchFilterDAO
    abstract val searchHistoryDAO: SearchHistoryDAO
    abstract val recipeFieldsInfoDao: RecipeFieldsInfoDao

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