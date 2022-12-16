package com.example.foodinfo.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodinfo.repository.RepositoryRecipeFieldsInfo
import com.example.foodinfo.repository.RepositorySearchFilter
import com.example.foodinfo.repository.model.LabelFilterEditModel
import com.example.foodinfo.repository.model.LabelHintModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class SearchFilterCategoryViewModel @Inject constructor(
    private val repositoryRecipeFieldsInfo: RepositoryRecipeFieldsInfo,
    private val repositorySearchFilter: RepositorySearchFilter
) : ViewModel() {

    private val _labels = MutableSharedFlow<List<LabelFilterEditModel>>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val labels: SharedFlow<List<LabelFilterEditModel>> = _labels.shareIn(
        viewModelScope, SharingStarted.WhileSubscribed(), 1
    )

    var categoryName: String = ""
        set(initializer) {
            if (initializer == field) return
            field = initializer
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    _labels.emit(repositorySearchFilter.getCategory(categoryName = field).labels)
                }
            }
        }

    fun getLabelHint(labelName: String): LabelHintModel {
        return repositoryRecipeFieldsInfo.getLabelHint(categoryName, labelName)
    }
}