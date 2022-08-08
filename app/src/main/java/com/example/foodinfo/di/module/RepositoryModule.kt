package com.example.foodinfo.di.module

import com.example.foodinfo.model.local.dao.*
import com.example.foodinfo.model.repository.*
import com.example.foodinfo.model.repository.impl.*
import com.example.foodinfo.utils.ResourcesProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRepositoryRecipes(
        recipesDAO: RecipesDAO, resourcesProvider: ResourcesProvider
    ): RepositoryRecipes {
        return RepositoryRecipesImpl(resourcesProvider, recipesDAO)
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
        categoryLabelsDAO: CategoryLabelsDAO, resourcesProvider: ResourcesProvider
    ): RepositoryCategoryLabels {
        return RepositoryCategoryLabelsImpl(resourcesProvider, categoryLabelsDAO)
    }

    @Provides
    @Singleton
    fun provideRepositoryNutrients(
        nutrientsDAO: NutrientsDAO, resourcesProvider: ResourcesProvider
    ): RepositoryNutrients {
        return RepositoryNutrientsImpl(resourcesProvider, nutrientsDAO)
    }
}