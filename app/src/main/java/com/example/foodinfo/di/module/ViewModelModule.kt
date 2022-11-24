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
    @ViewModelKey(SearchQueryViewModel::class)
    abstract fun bindsSearchQueryViewModel(viewModel: SearchQueryViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchLabelViewModel::class)
    abstract fun bindsSearchLabelViewModel(viewModel: SearchLabelViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchCategoryViewModel::class)
    abstract fun bindsSearchCategoryViewModel(viewModel: SearchCategoryViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    abstract fun bindsSettingsViewModel(viewModel: SettingsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PlannerViewModel::class)
    abstract fun bindsPlannerViewModel(viewModel: PlannerViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteViewModel::class)
    abstract fun bindsFavoriteViewModel(viewModel: FavoriteViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteSortViewModel::class)
    abstract fun bindsFavoriteSortViewModel(viewModel: FavoriteSortViewModel): ViewModel
}