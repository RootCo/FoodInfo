package com.example.foodinfo.di.module

import android.content.Context
import com.example.foodinfo.local.DataBase
import com.example.foodinfo.local.dao.RecipeFieldsInfoDao
import com.example.foodinfo.local.dao.RecipesDAO
import com.example.foodinfo.local.dao.SearchFilterDAO
import com.example.foodinfo.local.dao.SearchHistoryDAO
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RoomModule {
    @Singleton
    @Provides
    fun provideDataBase(context: Context): DataBase {
        return DataBase.getDatabase(context)
    }


    @Provides
    fun provideRecipeDAO(database: DataBase): RecipesDAO {
        return database.recipesDAO
    }

    @Provides
    fun provideSearchFilterDAO(database: DataBase): SearchFilterDAO {
        return database.searchFilterDAO
    }

    @Provides
    fun provideSearchInputHistoryDAO(database: DataBase): SearchHistoryDAO {
        return database.searchHistoryDAO
    }

    @Provides
    fun provideRecipeFieldsInfoDAO(database: DataBase): RecipeFieldsInfoDao {
        return database.recipeFieldsInfoDao
    }
}