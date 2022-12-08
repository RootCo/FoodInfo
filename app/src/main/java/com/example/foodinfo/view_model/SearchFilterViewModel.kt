package com.example.foodinfo.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodinfo.repository.RepositoryLabels
import com.example.foodinfo.repository.RepositorySearchFilter
import com.example.foodinfo.repository.model.CategoryLabelsModel
import com.example.foodinfo.repository.model.RangeFieldModel
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
    private val repositoryLabels: RepositoryLabels
) : ViewModel() {

    private val _rangeFields = MutableSharedFlow<List<RangeFieldModel>>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val rangeFields: SharedFlow<List<RangeFieldModel>> = _rangeFields.shareIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        1
    )

    private val _categories = MutableSharedFlow<List<CategoryLabelsModel>>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val categories: SharedFlow<List<CategoryLabelsModel>> = _categories.shareIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        1
    )

    val recipeId = ""


    init {
        viewModelScope.launch {
            withContext((Dispatchers.IO)) {
                _rangeFields.emit(repositorySearchFilter.getFieldsByCategory("base"))
            }
        }
        viewModelScope.launch {
            withContext((Dispatchers.IO)) {
                _categories.emit(repositoryLabels.getCategories().map { category ->
                    CategoryLabelsModel(
                        category.name,
                        listOf() //TODO get content from filter
                    )
                })
            }
        }
    }
}
