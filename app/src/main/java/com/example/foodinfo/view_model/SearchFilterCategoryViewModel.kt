package com.example.foodinfo.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodinfo.repository.RepositoryLabels
import com.example.foodinfo.repository.model.LabelFilterEditModel
import com.example.foodinfo.repository.model.LabelModel
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
    private val repositoryLabels: RepositoryLabels
) : ViewModel() {

    private val _labels = MutableSharedFlow<List<LabelFilterEditModel>>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val labels: SharedFlow<List<LabelFilterEditModel>> = _labels.shareIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        1
    )

    var categoryName: String = ""
        set(initializer) {
            if (initializer == field) {
                return
            }
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    _labels.emit(repositoryLabels.getLabelsFilterEdit(initializer))
                }
            }
            field = initializer
        }

    fun getLabel(labelName: String): LabelModel {
        return repositoryLabels.getLabel(categoryName, labelName)
    }
}