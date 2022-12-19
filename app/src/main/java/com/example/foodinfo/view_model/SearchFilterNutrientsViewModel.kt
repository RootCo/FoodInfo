package com.example.foodinfo.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodinfo.repository.RepositoryRecipeFieldsInfo
import com.example.foodinfo.repository.RepositorySearchFilter
import com.example.foodinfo.repository.model.NutrientFilterEditModel
import com.example.foodinfo.repository.model.NutrientHintModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class SearchFilterNutrientsViewModel @Inject constructor(
    private val repositoryRecipeFieldsInfo: RepositoryRecipeFieldsInfo,
    private val repositorySearchFilter: RepositorySearchFilter,
) : ViewModel() {

    private val _rangeFields = MutableSharedFlow<List<NutrientFilterEditModel>>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val rangeFields: SharedFlow<List<NutrientFilterEditModel>> = _rangeFields.shareIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        1
    )


    init {
        viewModelScope.launch {
            withContext((Dispatchers.IO)) {
                _rangeFields.emit(repositorySearchFilter.getNutrientsEdit())
            }
        }
    }

    fun getNutrient(name: String): NutrientHintModel {
        return repositoryRecipeFieldsInfo.getNutrientHint(name)
    }

    fun reset() {
        repositorySearchFilter.resetNutrients()
    }
}