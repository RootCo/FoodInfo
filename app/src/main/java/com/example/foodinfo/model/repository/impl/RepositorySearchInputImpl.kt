package com.example.foodinfo.model.repository.impl

import com.example.foodinfo.model.dao.SearchInputHistoryDAO
import com.example.foodinfo.model.repository.RepositorySearchInput
import javax.inject.Inject

class RepositorySearchInputImpl @Inject constructor(
    private val searchHistoryDAO: SearchInputHistoryDAO
) : RepositorySearchInput {

    override fun getHistory(): List<String> {
        return searchHistoryDAO.getHistory()
    }
}