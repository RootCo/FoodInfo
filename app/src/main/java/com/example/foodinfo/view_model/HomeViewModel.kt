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

    val dailyRecipe: StateFlow<RecipeExplore?> =
        repositoryRecipes.getDaily()
            .flowOn(Dispatchers.IO)
            .stateIn(
                viewModelScope,
                SharingStarted.Lazily,
                null
            )

    val recipes: StateFlow<PagingData<RecipeExplore>> =
        repositoryRecipes.getPopular()
            .cachedIn(viewModelScope)
            .flowOn(Dispatchers.IO)
            .stateIn(
                viewModelScope,
                SharingStarted.Lazily,
                PagingData.empty()
            )
}