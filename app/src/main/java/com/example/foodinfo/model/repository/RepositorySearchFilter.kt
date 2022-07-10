package com.example.foodinfo.model.repository

import com.example.foodinfo.model.entities.SearchFilter


interface RepositorySearchFilter {
    fun updateFilter()

    fun getFilter(): SearchFilter

    fun setFilter(filter: SearchFilter)

    fun getFromPreset(id: Int): SearchFilter

    fun addToPreset(filter: SearchFilter)

    fun delFromPreset(id: Int)

    fun toQuery(filter: SearchFilter): String
}