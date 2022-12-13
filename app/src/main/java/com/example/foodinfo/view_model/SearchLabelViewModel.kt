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


class SearchLabelViewModel @Inject constructor(
    private val repositoryRecipes: RepositoryRecipes,
    private val repositoryLabels: RepositoryLabels
) : ViewModel() {
    private val filter = SearchFilterModel()
    private lateinit var categoryName: String
    private lateinit var labelName: String

    val label: LabelModel by lazy {
        repositoryLabels.getLabel(categoryName, labelName)
    }

    fun setLabel(category: String, label: String) {
        this.categoryName = category
        this.labelName = label
        filter.categoryFields.add(CategoryField(category, listOf(label)))
        filter.buildQuery()
    }

    val recipes: StateFlow<PagingData<RecipeShortModel>> =
        repositoryRecipes.getByFilter(filter)
            .cachedIn(viewModelScope)
            .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    fun updateFavoriteMark(recipeId: String) {
        repositoryRecipes.updateFavoriteMark(recipeId)
    }
}