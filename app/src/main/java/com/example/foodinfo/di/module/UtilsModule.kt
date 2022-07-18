package com.example.foodinfo.di.module

import android.content.Context
import com.example.foodinfo.utils.AssetProvider
import com.example.foodinfo.utils.DecorationUtils
import com.example.foodinfo.utils.ResourcesProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class UtilsModule {

    @Provides
    @Singleton
    fun provideAssetProvider(context: Context): AssetProvider {
        return AssetProvider(context)
    }

    @Provides
    @Singleton
    fun provideResourcesProvider(context: Context): ResourcesProvider {
        return ResourcesProvider(context)
    }

    @Provides
    @Singleton
    fun provideDecorationUtils(context: Context): DecorationUtils {
        return DecorationUtils(context)
    }
}