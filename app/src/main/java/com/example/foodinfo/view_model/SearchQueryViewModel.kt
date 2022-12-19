package com.example.foodinfo.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.foodinfo.repository.RepositoryRecipes
import com.example.foodinfo.repository.RepositorySearchFilter
import com.example.foodinfo.repository.model.RecipeShortModel
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject


class SearchQueryViewModel @Inject constructor(
    private val repositoryRecipes: RepositoryRecipes,
    private val repositorySearchFilter: RepositorySearchFilter
) : ViewModel() {

    lateinit var inputText: String

    val recipes: SharedFlow<PagingData<RecipeShortModel>> by lazy {
        repositoryRecipes.getByFilter(repositorySearchFilter.getQueryByFilter(inputText = inputText))
            .cachedIn(viewModelScope)
            .shareIn(viewModelScope, SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000), 1)
    }


    fun updateFavoriteMark(recipeId: String) {
        repositoryRecipes.updateFavoriteMark(recipeId)
    }
}