package com.example.foodinfo.model.dao

import com.example.foodinfo.model.entities.SearchInput

interface SearchInputHistoryDAO {
    fun getHistory(): List<SearchInput>
}