package com.example.foodinfo.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodinfo.model.local.entities.SearchInput
import com.example.foodinfo.model.repository.RepositorySearchHistory
import javax.inject.Inject

class SearchInputViewModel @Inject constructor(
    private val repositorySearchHistory: RepositorySearchHistory
) : ViewModel() {

    private val _searchHistory: MutableLiveData<List<SearchInput>> by lazy {
        MutableLiveData<List<SearchInput>>()
    }
    val searchHistory: LiveData<List<SearchInput>>
        get() = _searchHistory

    fun updateSearchHistory(inputText: String = "") {
        _searchHistory.value = repositorySearchHistory.getHistoryLatest(inputText)
    }

    fun addToHistory(inputText: String){
        repositorySearchHistory.addInput(SearchInput(inputText = inputText))
    }
}