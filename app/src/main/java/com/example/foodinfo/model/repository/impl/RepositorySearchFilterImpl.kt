package com.example.foodinfo.model.repository.impl

import com.example.foodinfo.model.dao.SearchFilterDAO
import com.example.foodinfo.model.entities.SearchFilter
import com.example.foodinfo.model.repository.RepositorySearchFilter
import javax.inject.Inject


class RepositorySearchFilterImpl @Inject constructor(
    private val searchFilterDAO: SearchFilterDAO
) : RepositorySearchFilter {

    override fun updateFilter() {
        searchFilterDAO.updateFilter()
    }

    override fun getFilter(): SearchFilter {
        return searchFilterDAO.getFilter()
    }

    override fun setFilter(filter: SearchFilter) {
        searchFilterDAO.setFilter(filter)
    }

    override fun getFromPreset(id: Int): SearchFilter {
        return searchFilterDAO.getFromPreset(id)
    }

    override fun addToPreset(filter: SearchFilter) {
        searchFilterDAO.addToPreset(filter)
    }

    override fun delFromPreset(id: Int) {
        searchFilterDAO.delFromPreset(id)
    }

    override fun toQuery(filter: SearchFilter): String {
        return searchFilterDAO.toQuery(filter)
    }
}