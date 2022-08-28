package com.example.foodinfo.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.foodinfo.repository.RepositoryRecipes
import com.example.foodinfo.repository.model.RecipeShortModel
import com.example.foodinfo.repository.model.SearchFilterModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


class SearchQueryViewModel @Inject constructor(
    private val repositoryRecipes: RepositoryRecipes,
) : ViewModel() {
    private val filter = SearchFilterModel()

    fun setFilter(query: String) {
        filter.inputText = query
        filter.buildQuery()
    }

    val recipes: StateFlow<PagingData<RecipeShortModel>> =
        repositoryRecipes.getByFilter(filter)
            .flowOn(Dispatchers.IO)
            .cachedIn(viewModelScope)
            .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    fun updateFavoriteMark(recipeId: String) {
        repositoryRecipes.updateFavoriteMark(recipeId)
    }
}