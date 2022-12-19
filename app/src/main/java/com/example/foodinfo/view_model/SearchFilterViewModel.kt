package com.example.foodinfo.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodinfo.local.entity.SearchFilterEntity
import com.example.foodinfo.repository.RepositorySearchFilter
import com.example.foodinfo.repository.model.BaseFieldFilterEditModel
import com.example.foodinfo.repository.model.CategoryFilterPreviewModel
import com.example.foodinfo.repository.model.NutrientFilterPreviewModel
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject


class SearchFilterViewModel @Inject constructor(
    private val repositorySearchFilter: RepositorySearchFilter,
) : ViewModel() {

    val rangeFields: SharedFlow<List<BaseFieldFilterEditModel>> by lazy {
        repositorySearchFilter.getBaseFieldsEdit().shareIn(
            viewModelScope, SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000), 1
        )
    }

    val categories: SharedFlow<List<CategoryFilterPreviewModel>> by lazy {
        repositorySearchFilter.getCategoriesPreview().shareIn(
            viewModelScope, SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000), 1
        )
    }

    val nutrients: SharedFlow<List<NutrientFilterPreviewModel>> by lazy {
        repositorySearchFilter.getNutrientsPreview().shareIn(
            viewModelScope, SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000), 1
        )
    }

    var filterName = SearchFilterEntity.DEFAULT_NAME


    fun reset() {
        repositorySearchFilter.resetFilter()
    }

    fun updateField(id: Long, minValue: Float, maxValue: Float) {
        repositorySearchFilter.updateBaseField(id, minValue, maxValue)
    }
}
