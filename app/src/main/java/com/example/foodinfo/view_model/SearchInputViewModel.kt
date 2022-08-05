package com.example.foodinfo.view_model

import androidx.lifecycle.ViewModel
import com.example.foodinfo.model.local.entities.SearchInput
import com.example.foodinfo.model.repository.RepositorySearchHistory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class SearchInputViewModel @Inject constructor(
    private val repositorySearchHistory: RepositorySearchHistory
) : ViewModel() {

    private val _searchHistory = MutableStateFlow(
        repositorySearchHistory.getHistoryLatest("")
    )
    val searchHistory: StateFlow<List<SearchInput>> = _searchHistory.asStateFlow()

    fun updateSearchHistory(inputText: String = "") {
        _searchHistory.value = repositorySearchHistory.getHistoryLatest(inputText)
    }

    fun addToHistory(inputText: String) {
        repositorySearchHistory.addInput(SearchInput(inputText = inputText))
    }
}