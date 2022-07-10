package com.example.foodinfo.di.module

import dagger.Module
import dagger.android.AndroidInjectionModule

@Module(includes = [
    ContextModule::class,
    ProviderModule::class,
    RepositoryModule::class,
    ViewModelModule::class,
    ContextModule::class,
    DAOModule::class,
    AndroidInjectionModule::class
])
class BaseApplicationModule