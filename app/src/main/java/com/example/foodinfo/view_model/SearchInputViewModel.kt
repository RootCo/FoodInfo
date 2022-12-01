package com.example.foodinfo.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodinfo.repository.RepositorySearchHistory
import com.example.foodinfo.repository.model.SearchInputModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


class SearchInputViewModel @Inject constructor(
    private val repositorySearchHistory: RepositorySearchHistory
) : ViewModel() {

    var job: Job? = null

    private val _searchHistory = MutableSharedFlow<List<SearchInputModel>>()
    val searchHistory: SharedFlow<List<SearchInputModel>> = _searchHistory.asSharedFlow()
        .shareIn(viewModelScope, SharingStarted.WhileSubscribed(), 0)


    init {
        viewModelScope.launch(Dispatchers.IO) {
            _searchHistory.emit(repositorySearchHistory.getHistoryLatest(""))
        }
    }


    fun updateSearchHistory(inputText: String = "") {
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
            _searchHistory.emit(repositorySearchHistory.getHistoryLatest(inputText))
        }
    }

    fun addToHistory(inputText: String) {
        repositorySearchHistory.addInput(SearchInputModel(inputText = inputText))
    }
}