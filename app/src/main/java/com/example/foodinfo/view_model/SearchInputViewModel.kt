package com.example.foodinfo.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodinfo.local.model.SearchInput
import com.example.foodinfo.repository.RepositorySearchHistory
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class SearchInputViewModel @Inject constructor(
    private val repositorySearchHistory: RepositorySearchHistory
) : ViewModel() {

    private val _searchHistory = MutableStateFlow(
        repositorySearchHistory.getHistoryLatest("")
    )
    val searchHistory: StateFlow<List<SearchInput>> = _searchHistory.asStateFlow()
        .stateIn(viewModelScope, SharingStarted.Lazily, listOf())

    fun updateSearchHistory(inputText: String = "") {
        _searchHistory.value = repositorySearchHistory.getHistoryLatest(inputText)
    }

    fun addToHistory(inputText: String) {
        repositorySearchHistory.addInput(SearchInput(inputText = inputText))
    }
}