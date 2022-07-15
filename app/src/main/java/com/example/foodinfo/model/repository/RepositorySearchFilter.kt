package com.example.foodinfo.model.repository

import com.example.foodinfo.model.local.entities.SearchFilter


interface RepositorySearchFilter {
    fun getFilter(): SearchFilter

    fun getFromPreset(id: Long): SearchFilter

    fun addToPreset(filter: SearchFilter)

    fun delFromPreset(id: Long)
}