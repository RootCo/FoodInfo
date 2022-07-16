package com.example.foodinfo.model.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.foodinfo.model.local.entities.SearchInput


@Dao
interface SearchInputHistoryDAO {
    // пока не решил куда лучше вынести эту "7"
    @Query("${SearchInput.SELECTOR} LIMIT 7")
    fun getHistory(): List<SearchInput>
}