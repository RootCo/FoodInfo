package com.example.foodinfo.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodinfo.repository.RepositoryRecipeFieldsInfo
import com.example.foodinfo.repository.RepositorySearchFilter
import com.example.foodinfo.repository.model.CategoryFilterEditModel
import com.example.foodinfo.repository.model.LabelHintModel
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject


class SearchFilterCategoryViewModel @Inject constructor(
    private val repositoryRecipeFieldsInfo: RepositoryRecipeFieldsInfo,
    private val repositorySearchFilter: RepositorySearchFilter
) : ViewModel() {

    lateinit var categoryName: String

    val labels: SharedFlow<CategoryFilterEditModel> by lazy {
        repositorySearchFilter.getCategoryEdit(categoryName = categoryName).shareIn(
            viewModelScope, SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000), 1
        )
    }


    fun getLabelHint(labelName: String): LabelHintModel {
        return repositoryRecipeFieldsInfo.getLabelHint(categoryName, labelName)
    }

    fun reset() {
        repositorySearchFilter.resetCategory(categoryName = categoryName)
    }

    fun updateLabel(id: Long, isSelected: Boolean) {
        repositorySearchFilter.updateLabel(id, isSelected)
    }
}