package com.example.foodinfo.di.module

import android.content.Context
import com.example.foodinfo.model.local.DataBase
import com.example.foodinfo.model.local.dao.*
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
    fun provideCategoryLabelsDAO(database: DataBase): LabelsDAO {
        return database.labelsDAO
    }

    @Provides
    fun provideNutrientsDAO(database: DataBase): NutrientsDAO {
        return database.nutrientsDAO
    }
}