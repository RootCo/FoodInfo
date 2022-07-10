package com.example.foodinfo.model.dao

interface SearchInputHistoryDAO {
    fun getHistory(): List<String>
}