package com.example.foodinfo.model.repository

import com.example.foodinfo.model.local.SearchInput


interface RepositorySearchHistory {
    fun getHistoryLatest(inputText: String): List<SearchInput>

    fun getHistoryAll(): List<SearchInput>

    fun addHistory(searchHistory: List<SearchInput>)

    fun addInput(searchInput: SearchInput)
}