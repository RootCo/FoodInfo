package com.example.foodinfo.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.foodinfo.repository.RepositoryLabels
import com.example.foodinfo.repository.RepositoryRecipes
import com.example.foodinfo.repository.model.LabelModel
import com.example.foodinfo.repository.model.RecipeShortModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


class SearchTargetViewModel @Inject constructor(
    repositoryRecipes: RepositoryRecipes,
    private val repositoryLabels: RepositoryLabels
) : ViewModel() {
    val featureName = "Search recipes"

    fun getLabel(category: String, label: String): LabelModel {
        return repositoryLabels.getByLabel(category, label)
    }

    val recipes: StateFlow<PagingData<RecipeShortModel>> =
        repositoryRecipes.getPopular()
            .flowOn(Dispatchers.IO)
            .cachedIn(viewModelScope)
            .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
}