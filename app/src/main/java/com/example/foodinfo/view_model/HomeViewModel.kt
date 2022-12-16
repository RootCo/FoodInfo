package com.example.foodinfo.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodinfo.repository.RepositoryRecipeFieldsInfo
import com.example.foodinfo.repository.model.CategoryFieldModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class HomeViewModel @Inject constructor(
    repositoryRecipeFieldsInfo: RepositoryRecipeFieldsInfo
) : ViewModel() {

    private val _categories = MutableSharedFlow<List<CategoryFieldModel>>()
    val categories: SharedFlow<List<CategoryFieldModel>> = _categories.shareIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        1
    )


    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _categories.emit(repositoryRecipeFieldsInfo.getCategories())
            }
        }
    }
}