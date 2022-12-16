package com.example.foodinfo.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodinfo.repository.RepositoryRecipeFieldsInfo
import com.example.foodinfo.repository.model.CategoryFieldModel
import com.example.foodinfo.repository.model.LabelSearchModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class SearchCategoryViewModel @Inject constructor(
    private val repositoryRecipeFieldsInfo: RepositoryRecipeFieldsInfo
) : ViewModel() {

    var categoryName: String = ""
        set(initializer) {
            if (initializer == field) {
                return
            }
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    _labels.emit(repositoryRecipeFieldsInfo.getLabelsSearch(initializer))
                }
            }
            field = initializer
        }

    private val _labels = MutableSharedFlow<List<LabelSearchModel>>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val labels: SharedFlow<List<LabelSearchModel>> = _labels.shareIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        0
    )

    val category: CategoryFieldModel by lazy {
        repositoryRecipeFieldsInfo.getCategory(categoryName)
    }
}