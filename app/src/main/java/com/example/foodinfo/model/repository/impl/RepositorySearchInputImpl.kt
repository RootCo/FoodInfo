package com.example.foodinfo.model.repository.impl

import com.example.foodinfo.model.local.dao.SearchInputHistoryDAO
import com.example.foodinfo.model.local.entities.SearchInput
import com.example.foodinfo.model.repository.RepositorySearchInput
import javax.inject.Inject


class RepositorySearchInputImpl @Inject constructor(
    private val searchHistoryDAO: SearchInputHistoryDAO
) : RepositorySearchInput {

    override fun getHistory(): List<SearchInput> {
        return searchHistoryDAO.getHistory()
    }
}