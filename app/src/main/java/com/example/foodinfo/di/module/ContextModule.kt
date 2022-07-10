package com.example.foodinfo.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ContextModule {

    @Singleton
    @Provides
    fun context(application: Application): Context {
        return application
    }
}