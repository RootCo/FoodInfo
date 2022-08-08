package com.example.foodinfo.model.repository.impl

import com.example.foodinfo.model.local.SearchInput
import com.example.foodinfo.model.local.dao.SearchHistoryDAO
import com.example.foodinfo.model.repository.RepositorySearchHistory
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