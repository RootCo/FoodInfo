package com.example.foodinfo.repository.impl

import com.example.foodinfo.local.model.SearchInput
import com.example.foodinfo.local.dao.SearchHistoryDAO
import com.example.foodinfo.repository.RepositorySearchHistory
import javax.inject.Inject


class RepositorySearchHistoryImpl @Inject constructor(
    private val searchHistoryDAO: SearchHistoryDAO
) : RepositorySearchHistory {

    override fun getHistoryLatest(inputText: String): List<SearchInput> {
        return searchHistoryDAO.getHistoryLatest(inputText).map { entity ->
            SearchInput.fromEntity(entity)
        }
    }

    override fun getHistoryAll(): List<SearchInput> {
        return searchHistoryDAO.getHistoryAll().map { entity ->
            SearchInput.fromEntity(entity)
        }
    }

    override fun addHistory(searchHistory: List<SearchInput>) {
        searchHistoryDAO.addHistory(searchHistory.map { SearchInput.toEntity(it) })
    }

    override fun addInput(searchInput: SearchInput) {
        searchHistoryDAO.addInput(SearchInput.toEntity(searchInput))
    }
}