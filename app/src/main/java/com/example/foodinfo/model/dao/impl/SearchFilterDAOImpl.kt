package com.example.foodinfo.model.dao.impl

import com.example.foodinfo.model.dao.SearchFilterDAO
import com.example.foodinfo.model.entities.SearchFilter
import java.util.*


class SearchFilterDAOImpl : SearchFilterDAO {
    private var filter: SearchFilter = SearchFilter(Calendar.getInstance().toString())
    private var filterPreset: List<SearchFilter> = arrayListOf()

    override fun updateFilter() {
        filter = SearchFilter(Calendar.getInstance().toString())
    }

    override fun getFilter(): SearchFilter {
        return filter
    }

    override fun setFilter(filter: SearchFilter) {
        this.filter = filter
    }

    override fun getFromPreset(id: Int): SearchFilter {
        return filter
    }

    override fun addToPreset(filter: SearchFilter) {

        // TODO add
    }

    override fun delFromPreset(id: Int) {

        // TODO del
    }

    override fun toQuery(filter: SearchFilter): String {

        // TODO конвертация SearchFilter в query String
        return filter.str
    }
}