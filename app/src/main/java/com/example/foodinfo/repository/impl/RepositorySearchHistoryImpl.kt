package com.example.foodinfo.repository.impl

import com.example.foodinfo.local.dao.SearchHistoryDAO
import com.example.foodinfo.repository.RepositorySearchHistory
import com.example.foodinfo.repository.mapper.toEntity
import com.example.foodinfo.repository.mapper.toModel
import com.example.foodinfo.repository.model.SearchInputModel
import javax.inject.Inject


class RepositorySearchHistoryImpl @Inject constructor(
    private val searchHistoryDAO: SearchHistoryDAO
) : RepositorySearchHistory {

    override suspend fun getHistoryLatest(inputText: String): List<SearchInputModel> {
        return searchHistoryDAO.getHistoryLatest(inputText).map { it.toModel() }
    }

    override fun getHistoryAll(): List<SearchInputModel> {
        return searchHistoryDAO.getHistoryAll().map { it.toModel() }
    }

    override fun addHistory(searchHistory: List<SearchInputModel>) {
        searchHistoryDAO.addHistory(searchHistory.map { it.toEntity() })
    }

    override fun addInput(searchInput: SearchInputModel) {
        searchHistoryDAO.addInput(searchInput.toEntity())
    }
}