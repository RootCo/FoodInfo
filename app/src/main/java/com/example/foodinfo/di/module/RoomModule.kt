package com.example.foodinfo.di.module

import android.content.Context
import com.example.foodinfo.model.local.DataBase
import com.example.foodinfo.model.local.dao.RecipesDAO
import com.example.foodinfo.model.local.dao.SearchFilterDAO
import com.example.foodinfo.model.local.dao.SearchHistoryDAO
import com.example.foodinfo.utils.AssetProvider
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton


@Module
class RoomModule {
    @Singleton
    @Provides
    fun provideDataBase(
        context: Context,
        assetProvider: AssetProvider,
        scope: CoroutineScope
    ): DataBase {
        return DataBase.getDatabase(context, assetProvider, scope)
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

    // заглушка чтобы напихать в бд данные при первой инизиализации
    @Singleton
    @Provides
    fun providesCoroutineScope(): CoroutineScope {
        return CoroutineScope(SupervisorJob() + Dispatchers.IO)
    }
}