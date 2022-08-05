package com.example.foodinfo.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.foodinfo.model.local.RecipeExplore
import com.example.foodinfo.model.repository.RepositoryRecipes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


class HomeViewModel @Inject constructor(
    repositoryRecipes: RepositoryRecipes
) : ViewModel() {
    val recipes: StateFlow<PagingData<RecipeExplore>> =
        repositoryRecipes.getPopular()
            .flowOn(Dispatchers.IO)
            .cachedIn(viewModelScope)
            .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
}