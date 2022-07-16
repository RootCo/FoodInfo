package com.example.foodinfo.di.module

import com.example.foodinfo.model.local.dao.RecipesDAO
import com.example.foodinfo.model.local.dao.SearchFilterDAO
import com.example.foodinfo.model.local.dao.SearchHistoryDAO
import com.example.foodinfo.model.repository.RepositoryRecipes
import com.example.foodinfo.model.repository.RepositorySearchFilter
import com.example.foodinfo.model.repository.RepositorySearchHistory
import com.example.foodinfo.model.repository.impl.RepositoryRecipesImpl
import com.example.foodinfo.model.repository.impl.RepositorySearchFilterImpl
import com.example.foodinfo.model.repository.impl.RepositorySearchHistoryImpl
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
    fun provideRepositorySearchFilter(searchFilterDAO: SearchFilterDAO): RepositorySearchFilter {
        return RepositorySearchFilterImpl(searchFilterDAO)
    }

    @Provides
    @Singleton
    fun provideRepositorySearchInput(searchHistoryDAO: SearchHistoryDAO): RepositorySearchHistory {
        return RepositorySearchHistoryImpl(searchHistoryDAO)
    }
}