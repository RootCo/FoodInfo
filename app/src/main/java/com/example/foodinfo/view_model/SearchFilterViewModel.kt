package com.example.foodinfo.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodinfo.local.entity.SearchFilterEntity
import com.example.foodinfo.repository.RepositorySearchFilter
import com.example.foodinfo.repository.model.BaseFieldFilterEditModel
import com.example.foodinfo.repository.model.CategoryFilterPreviewModel
import com.example.foodinfo.repository.model.NutrientFilterPreviewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class SearchFilterViewModel @Inject constructor(
    private val repositorySearchFilter: RepositorySearchFilter,
) : ViewModel() {

    private val _rangeFields = MutableSharedFlow<List<BaseFieldFilterEditModel>>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val rangeFields: SharedFlow<List<BaseFieldFilterEditModel>> = _rangeFields.shareIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        1
    )

    private val _categories = MutableSharedFlow<List<CategoryFilterPreviewModel>>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val categories: SharedFlow<List<CategoryFilterPreviewModel>> = _categories.shareIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        1
    )

    private val _nutrients = MutableSharedFlow<List<NutrientFilterPreviewModel>>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val nutrients: SharedFlow<List<NutrientFilterPreviewModel>> = _nutrients.shareIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        1
    )

    var filterName = SearchFilterEntity.DEFAULT_NAME


    init {
        viewModelScope.launch {
            withContext((Dispatchers.IO)) {
                _rangeFields.emit(repositorySearchFilter.getBaseFields())
            }
        }
        viewModelScope.launch {
            withContext((Dispatchers.IO)) {
                _nutrients.emit(repositorySearchFilter.getNutrientsPreview())
            }
        }
        viewModelScope.launch {
            withContext((Dispatchers.IO)) {
                _categories.emit(repositorySearchFilter.getCategoriesPreview())
            }
        }
    }
}
