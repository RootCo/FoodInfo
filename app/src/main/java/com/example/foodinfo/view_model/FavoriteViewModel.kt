package com.example.foodinfo.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.foodinfo.repository.RepositoryRecipes
import com.example.foodinfo.repository.model.RecipeFavoriteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject


class FavoriteViewModel @Inject constructor(
    private val repositoryRecipes: RepositoryRecipes
) : ViewModel() {

    private val selectedRecipes: ArrayList<String> = arrayListOf()

    private val _selectedCount = MutableStateFlow(0)
    val selectedCount: StateFlow<Int> = _selectedCount.asStateFlow()

    private val _isEditMode = MutableStateFlow(false)
    val isEditMode: StateFlow<Boolean> = _isEditMode.asStateFlow()

    val recipes: StateFlow<PagingData<RecipeFavoriteModel>> =
        repositoryRecipes.getFavorite()
            .flowOn(Dispatchers.IO)
            .cachedIn(viewModelScope)
            .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())


    fun setEditMode(isEdit: Boolean) {
        _isEditMode.value = isEdit
    }

    fun isSelected(id: String): Boolean {
        return id in selectedRecipes
    }

    fun delSelected() {
        repositoryRecipes.delFromFavorite(selectedRecipes)
    }

    fun updateSelectStatus(id: String) {
        if (id in selectedRecipes) {
            selectedRecipes.remove(id)
        } else {
            selectedRecipes.add(id)
        }
        _selectedCount.value = selectedRecipes.size
    }

    fun unselectAll() {
        selectedRecipes.clear()
        _selectedCount.value = selectedRecipes.size
    }

    fun updateSelected() {
        selectedRecipes.removeAll { recipeId ->
            recipeId !in repositoryRecipes.getFavoriteIds()
        }
        _selectedCount.value = selectedRecipes.size
    }
}