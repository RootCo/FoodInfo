package com.example.foodinfo.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.foodinfo.repository.RepositoryRecipes
import com.example.foodinfo.repository.RepositorySearchFilter
import com.example.foodinfo.repository.model.RecipeShortModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class SearchQueryViewModel @Inject constructor(
    private val repositoryRecipes: RepositoryRecipes,
    private val repositorySearchFilter: RepositorySearchFilter
) : ViewModel() {

    private val _recipes = MutableSharedFlow<PagingData<RecipeShortModel>>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val recipes: SharedFlow<PagingData<RecipeShortModel>> = _recipes
        .cachedIn(viewModelScope)
        .shareIn(viewModelScope, SharingStarted.WhileSubscribed(), 5000)


    fun setInputText(inputText: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val query = repositorySearchFilter.getQueryByFilter(inputText = inputText)
                repositoryRecipes.getByFilter(query).collectLatest {
                    _recipes.emit(it)
                }
            }
        }
    }

    fun updateFavoriteMark(recipeId: String) {
        repositoryRecipes.updateFavoriteMark(recipeId)
    }
}