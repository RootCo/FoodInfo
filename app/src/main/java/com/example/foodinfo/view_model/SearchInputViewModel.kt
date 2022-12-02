package com.example.foodinfo.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodinfo.repository.RepositorySearchHistory
import com.example.foodinfo.repository.model.SearchInputModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class SearchInputViewModel @Inject constructor(
    private val repositorySearchHistory: RepositorySearchHistory
) : ViewModel() {

    private var job: Job? = null

    private val _searchHistory = MutableSharedFlow<List<SearchInputModel>>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val searchHistory: SharedFlow<List<SearchInputModel>> = _searchHistory.shareIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        0
    )


    init {
        viewModelScope.launch {
            withContext((Dispatchers.IO)) {
                _searchHistory.emit(repositorySearchHistory.getHistoryLatest(""))
            }
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