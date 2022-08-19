package com.example.foodinfo.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.foodinfo.repository.RepositoryLabels
import com.example.foodinfo.repository.RepositoryRecipes
import com.example.foodinfo.repository.model.LabelModel
import com.example.foodinfo.repository.model.RecipeShortModel
import com.example.foodinfo.repository.model.SearchFilterModel
import com.example.foodinfo.repository.model.filter_field.CategoryField
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
    private val filter = SearchFilterModel()

    fun getLabel(category: String, label: String): LabelModel {
        return repositoryLabels.getByLabel(category, label)
    }

    fun setFilter(category: String, label: String) {
        filter.categoryFields.add(CategoryField(category, listOf(label)))
        filter.buildQuery()
    }

    val recipes: StateFlow<PagingData<RecipeShortModel>> =
        repositoryRecipes.getByFilter(filter)
            .flowOn(Dispatchers.IO)
            .cachedIn(viewModelScope)
            .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
}