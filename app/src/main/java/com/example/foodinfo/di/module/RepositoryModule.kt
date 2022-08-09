package com.example.foodinfo.di.module

import com.example.foodinfo.local.dao.*
import com.example.foodinfo.repository.*
import com.example.foodinfo.repository.impl.*
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
    fun provideRepositoryCategoryLabels(
        labelsDAO: LabelsDAO
    ): RepositoryLabels {
        return RepositoryLabelsImpl(labelsDAO)
    }

    @Provides
    @Singleton
    fun provideRepositoryNutrients(
        nutrientsDAO: NutrientsDAO
    ): RepositoryNutrients {
        return RepositoryNutrientsImpl(nutrientsDAO)
    }
}