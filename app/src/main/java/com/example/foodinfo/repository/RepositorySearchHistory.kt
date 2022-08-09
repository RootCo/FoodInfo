package com.example.foodinfo.repository

import com.example.foodinfo.local.model.SearchInput


interface RepositorySearchHistory {
    fun getHistoryLatest(inputText: String): List<SearchInput>

    fun getHistoryAll(): List<SearchInput>

    fun addHistory(searchHistory: List<SearchInput>)

    fun addInput(searchInput: SearchInput)
}