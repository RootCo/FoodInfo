package com.example.foodinfo.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodinfo.model.repository.RepositorySearchInput
import javax.inject.Inject

class SearchInputViewModel @Inject constructor(
    private val repositorySearchInput: RepositorySearchInput
) : ViewModel() {

    private val _searchInputHistory: MutableLiveData<List<String>> by lazy {
        MutableLiveData<List<String>>()
    }
    val searchInputHistory: LiveData<List<String>>
        get() = _searchInputHistory

    fun updateSearchInput() {
        _searchInputHistory.value = repositorySearchInput.getHistory()
    }
}