package com.example.foodinfo.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.foodinfo.local.dao.*
import com.example.foodinfo.local.entity.LabelEntity
import com.example.foodinfo.local.entity.NutrientEntity
import com.example.foodinfo.local.entity.RecipeIngredientEntity
import com.example.foodinfo.local.entity.RecipeLabelEntity
import com.example.foodinfo.local.entity.RecipeNutrientEntity
import com.example.foodinfo.local.entity.SearchFilterEntity
import com.example.foodinfo.local.entity.SearchInputEntity
import com.example.foodinfo.local.entity.RecipeEntity


@Database(
    version = 1,
    entities = [
        RecipeEntity::class,
        SearchFilterEntity::class,
        SearchInputEntity::class,
        RecipeIngredientEntity::class,
        RecipeNutrientEntity::class,
        RecipeLabelEntity::class,
        LabelEntity::class,
        NutrientEntity::class,
    ]
)
abstract class DataBase : RoomDatabase() {
    abstract val recipesDAO: RecipesDAO
    abstract val searchFilterDAO: SearchFilterDAO
    abstract val searchHistoryDAO: SearchHistoryDAO
    abstract val labelsDAO: LabelsDAO
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