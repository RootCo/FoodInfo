package com.example.foodinfo.di.module

import com.example.foodinfo.model.dao.FoodDAO
import com.example.foodinfo.model.dao.RecipesDAO
import com.example.foodinfo.model.dao.SearchFilterDAO
import com.example.foodinfo.model.dao.SearchInputHistoryDAO
import com.example.foodinfo.model.dao.impl.FoodDAOImpl
import com.example.foodinfo.model.dao.impl.RecipesDAOImpl
import com.example.foodinfo.model.dao.impl.SearchFilterDAOImpl
import com.example.foodinfo.model.dao.impl.SearchInputHistoryDAOImpl
import com.example.foodinfo.utils.AssetProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DAOModule {
    @Provides
    @Singleton
    fun provideFoodDAO(assetProvider: AssetProvider): FoodDAO {
        return FoodDAOImpl(assetProvider)
    }

    @Provides
    @Singleton
    fun provideRecipesDAO(assetProvider: AssetProvider): RecipesDAO {
        return RecipesDAOImpl(assetProvider)
    }

    @Provides
    @Singleton
    fun provideSearchFilterDAO(): SearchFilterDAO {
        return SearchFilterDAOImpl()
    }

    @Provides
    @Singleton
    fun provideSearchInputHistoryDAO(assetProvider: AssetProvider): SearchInputHistoryDAO {
        return SearchInputHistoryDAOImpl(assetProvider)
    }
}