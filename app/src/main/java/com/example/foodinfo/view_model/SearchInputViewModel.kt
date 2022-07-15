package com.example.foodinfo.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodinfo.model.local.entities.SearchInput
import com.example.foodinfo.model.repository.RepositorySearchInput
import javax.inject.Inject

class SearchInputViewModel @Inject constructor(
    private val repositorySearchInput: RepositorySearchInput
) : ViewModel() {

    private val _searchInputHistory: MutableLiveData<List<SearchInput>> by lazy {
        MutableLiveData<List<SearchInput>>()
    }
    val searchInputHistory: LiveData<List<SearchInput>>
        get() = _searchInputHistory

    fun updateSearchInput() {
        _searchInputHistory.value = repositorySearchInput.getHistory()
    }
}