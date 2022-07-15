package com.example.foodinfo.model.repository

import com.example.foodinfo.model.local.entities.SearchInput


interface RepositorySearchInput {
    fun getHistory(): List<SearchInput>
}