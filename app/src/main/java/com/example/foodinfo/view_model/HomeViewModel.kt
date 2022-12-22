package com.example.foodinfo.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodinfo.repository.RepositoryRecipeFieldsInfo
import com.example.foodinfo.repository.model.CategoryFieldModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject


class HomeViewModel @Inject constructor(
    repositoryRecipeFieldsInfo: RepositoryRecipeFieldsInfo
) : ViewModel() {

    val categories: SharedFlow<List<CategoryFieldModel>> = flow {
        emit(repositoryRecipeFieldsInfo.getCategories())
    }.flowOn(Dispatchers.IO).shareIn(
        viewModelScope, SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000), 1
    )
}