package com.example.foodinfo.di.module

import com.example.foodinfo.local.dao.RecipeFieldsInfoDao
import com.example.foodinfo.local.dao.RecipesDAO
import com.example.foodinfo.local.dao.SearchFilterDAO
import com.example.foodinfo.local.dao.SearchHistoryDAO
import com.example.foodinfo.repository.RepositoryRecipeFieldsInfo
import com.example.foodinfo.repository.RepositoryRecipes
import com.example.foodinfo.repository.RepositorySearchFilter
import com.example.foodinfo.repository.RepositorySearchHistory
import com.example.foodinfo.repository.impl.RepositoryRecipeFieldsInfoImpl
import com.example.foodinfo.repository.impl.RepositoryRecipesImpl
import com.example.foodinfo.repository.impl.RepositorySearchFilterImpl
import com.example.foodinfo.repository.impl.RepositorySearchHistoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRepositoryRecipes(
        recipesDAO: RecipesDAO
    ): RepositoryRecipes {
        return RepositoryRecipesImpl(recipesDAO)
    }

    @Provides
    @Singleton
    fun provideRepositorySearchFilter(
        searchFilterDAO: SearchFilterDAO
    ): RepositorySearchFilter {
        return RepositorySearchFilterImpl(searchFilterDAO)
    }

    @Provides
    @Singleton
    fun provideRepositorySearchInput(
        searchHistoryDAO: SearchHistoryDAO
    ): RepositorySearchHistory {
        return RepositorySearchHistoryImpl(searchHistoryDAO)
    }

    @Provides
    @Singleton
    fun provideRepositoryRepositoryRecipeFieldsInfo(
        recipeFieldsInfoDao: RecipeFieldsInfoDao
    ): RepositoryRecipeFieldsInfo {
        return RepositoryRecipeFieldsInfoImpl(recipeFieldsInfoDao)
    }
}