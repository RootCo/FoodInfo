package com.example.foodinfo.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.foodinfo.repository.RepositoryRecipeFieldsInfo
import com.example.foodinfo.repository.RepositoryRecipes
import com.example.foodinfo.repository.RepositorySearchFilter
import com.example.foodinfo.repository.model.LabelHintModel
import com.example.foodinfo.repository.model.RecipeShortModel
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject


class SearchLabelViewModel @Inject constructor(
    private val repositoryRecipes: RepositoryRecipes,
    private val repositoryRecipeFieldsInfo: RepositoryRecipeFieldsInfo,
    private val repositorySearchFilter: RepositorySearchFilter
) : ViewModel() {

    lateinit var categoryName: String
    lateinit var labelName: String

    val recipes: SharedFlow<PagingData<RecipeShortModel>> by lazy {
        repositoryRecipes.getByFilter(repositorySearchFilter.getQueryByLabel(categoryName, labelName))
            .cachedIn(viewModelScope)
            .shareIn(viewModelScope, SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000), 1)
    }

    fun invertFavoriteStatus(recipeId: String) {
        repositoryRecipes.invertFavoriteStatus(recipeId)
    }

    fun getLabel(category: String, label: String): LabelHintModel {
        return repositoryRecipeFieldsInfo.getLabelHint(category, label)
    }
}