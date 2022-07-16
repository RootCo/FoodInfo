package com.example.foodinfo.model.repository.impl

import com.example.foodinfo.model.local.dao.SearchFilterDAO
import com.example.foodinfo.model.local.entities.SearchFilter
import com.example.foodinfo.model.repository.RepositorySearchFilter
import javax.inject.Inject


class RepositorySearchFilterImpl @Inject constructor(
    private val searchFilterDAO: SearchFilterDAO
) : RepositorySearchFilter {

    override fun getTarget(): SearchFilter {
        return searchFilterDAO.getTarget()
    }

    override fun delTarget() {
        searchFilterDAO.delTarget()
    }

    override fun getFromPreset(id: Long): SearchFilter {
        return searchFilterDAO.getFromPreset(id)
    }

    override fun getAllFromPreset(): List<SearchFilter> {
        return searchFilterDAO.getAllFromPreset()
    }

    override fun addToPreset(filter: SearchFilter) {
        searchFilterDAO.addToPreset(filter)
    }

    override fun delFromPreset(id: Long) {
        searchFilterDAO.delFromPreset(id)
    }
}