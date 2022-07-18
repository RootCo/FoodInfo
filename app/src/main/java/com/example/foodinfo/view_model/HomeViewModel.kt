package com.example.foodinfo.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.foodinfo.model.local.RecipeExplore
import com.example.foodinfo.model.repository.RepositoryRecipes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    repositoryRecipes: RepositoryRecipes
) : ViewModel() {

    val dailyRecipe: Flow<RecipeExplore?> =
        repositoryRecipes.getDaily().flowOn(Dispatchers.IO)

    val recipes: Flow<PagingData<RecipeExplore>> =
        repositoryRecipes.getPopular().cachedIn(viewModelScope).flowOn(Dispatchers.IO)
}