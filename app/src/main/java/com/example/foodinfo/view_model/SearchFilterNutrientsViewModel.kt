package com.example.foodinfo.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodinfo.repository.RepositoryRecipeFieldsInfo
import com.example.foodinfo.repository.RepositorySearchFilter
import com.example.foodinfo.repository.model.NutrientFilterEditModel
import com.example.foodinfo.repository.model.NutrientHintModel
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject


class SearchFilterNutrientsViewModel @Inject constructor(
    private val repositoryRecipeFieldsInfo: RepositoryRecipeFieldsInfo,
    private val repositorySearchFilter: RepositorySearchFilter,
) : ViewModel() {

    val nutrients: SharedFlow<List<NutrientFilterEditModel>> by lazy {
        repositorySearchFilter.getNutrientsEdit().shareIn(
            viewModelScope, SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000), 1
        )
    }


    fun getNutrient(name: String): NutrientHintModel {
        return repositoryRecipeFieldsInfo.getNutrientHint(name)
    }

    fun reset() {
        repositorySearchFilter.resetNutrients()
    }

    fun updateField(id: Long, minValue: Float, maxValue: Float) {
        repositorySearchFilter.updateNutrient(id, minValue, maxValue)
    }
}