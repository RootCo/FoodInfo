package com.example.foodinfo.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.foodinfo.model.local.CategoryItem
import com.example.foodinfo.model.local.RecipeExplore
import com.example.foodinfo.model.local.dao.filter_field.CategoryField
import com.example.foodinfo.model.local.entities.SearchFilter
import com.example.foodinfo.model.repository.RepositoryRecipes
import com.example.foodinfo.model.repository.impl.RepositoryRecipesImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


class ExploreViewModel @Inject constructor(
    private val repositoryRecipes: RepositoryRecipes
) : ViewModel() {

    val categories = CategoryField.Fields.values().map { it.label }
    val categoryRecipes = hashMapOf<String, StateFlow<PagingData<CategoryItem>>>()

    private val getRecipes: (SearchFilter) -> StateFlow<PagingData<RecipeExplore>> =
        { filter ->
            repositoryRecipes.getByFilterExplore(filter)
                .flowOn(Dispatchers.IO)
                .cachedIn(viewModelScope)
                .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
        }

    init {
        for (category in categories) {
            categoryRecipes[category] = Pager(
                config = RepositoryRecipesImpl.DB_EXPLORE_OUTER_PAGER,
                pagingSourceFactory = {
                    ExploreCategoriesDataSource(getRecipes, category)
                }).flow
                .flowOn(Dispatchers.IO)
                .cachedIn(viewModelScope)
                .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
        }
    }
}