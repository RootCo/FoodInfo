package com.example.foodinfo.model.repository

import com.example.foodinfo.model.local.entities.SearchFilter


interface RepositorySearchFilter {
    fun getTarget(): SearchFilter

    fun delTarget()

    fun getFromPreset(id: Long): SearchFilter

    fun getAllFromPreset(): List<SearchFilter>

    fun addToPreset(filter: SearchFilter)

    fun delFromPreset(id: Long)
}