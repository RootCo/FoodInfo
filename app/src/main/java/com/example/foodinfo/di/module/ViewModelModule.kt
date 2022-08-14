package com.example.foodinfo.di.module

import androidx.lifecycle.ViewModel
import com.example.foodinfo.view_model.*
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass


@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)


@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindsHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ExploreViewModel::class)
    abstract fun bindsExploreViewModel(viewModel: ExploreViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RecipeExtendedViewModel::class)
    abstract fun bindsRecipeExtendedViewModel(viewModel: RecipeExtendedViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RecipeIngredientsViewModel::class)
    abstract fun bindsRecipeIngredientsViewModel(viewModel: RecipeIngredientsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RecipeNutrientsViewModel::class)
    abstract fun bindsRecipeNutrientsViewModel(viewModel: RecipeNutrientsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchFilterViewModel::class)
    abstract fun bindsSearchFilterViewModel(viewModel: SearchFilterViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchInputViewModel::class)
    abstract fun bindsSearchInputViewModel(viewModel: SearchInputViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchResultViewModel::class)
    abstract fun bindsSearchResultViewModel(viewModel: SearchResultViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchTargetViewModel::class)
    abstract fun bindsSearchTargetViewModel(viewModel: SearchTargetViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    abstract fun bindsSettingsViewModel(viewModel: SettingsViewModel): ViewModel
}