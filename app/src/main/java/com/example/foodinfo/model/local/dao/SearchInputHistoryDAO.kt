package com.example.foodinfo.model.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.foodinfo.model.local.entities.SearchInput


@Dao
interface SearchInputHistoryDAO {
    @Query(SearchInput.SELECTOR)
    fun getHistory(): List<SearchInput>
}