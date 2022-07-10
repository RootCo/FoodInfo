package com.example.foodinfo.di.module

import com.example.foodinfo.model.dao.FoodDAO
import com.example.foodinfo.model.dao.RecipesDAO
import com.example.foodinfo.model.dao.SearchFilterDAO
import com.example.foodinfo.model.dao.SearchInputHistoryDAO
import com.example.foodinfo.model.repository.RepositoryFood
import com.example.foodinfo.model.repository.RepositoryRecipes
import com.example.foodinfo.model.repository.RepositorySearchFilter
import com.example.foodinfo.model.repository.RepositorySearchInput
import com.example.foodinfo.model.repository.impl.RepositoryFoodImpl
import com.example.foodinfo.model.repository.impl.RepositoryRecipesImpl
import com.example.foodinfo.model.repository.impl.RepositorySearchFilterImpl
import com.example.foodinfo.model.repository.impl.RepositorySearchInputImpl
import com.example.foodinfo.utils.ResourcesProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRepositoryFood(foodDAO: FoodDAO): RepositoryFood {
        return RepositoryFoodImpl(foodDAO)
    }

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
    fun provideRepositorySearchInput(searchInputHistoryDAO: SearchInputHistoryDAO): RepositorySearchInput {
        return RepositorySearchInputImpl(searchInputHistoryDAO)
    }
}