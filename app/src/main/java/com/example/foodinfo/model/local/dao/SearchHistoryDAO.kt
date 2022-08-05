package com.example.foodinfo.model.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.foodinfo.model.local.entities.SearchInput


@Dao
interface SearchHistoryDAO {
    @Query(
        "${SearchInput.SELECTOR} " +
                "WHERE ${SearchInput.Columns.INPUT_TEXT} " +
                "LIKE '%' || :inputText || '%' " +
                "ORDER BY ${SearchInput.Columns.DATE} DESC " +
                "LIMIT ${SearchInput.LIMIT}"
    )
    fun getHistoryLatest(inputText: String): List<SearchInput>

    @Query("${SearchInput.SELECTOR} ORDER BY ${SearchInput.Columns.DATE}")
    fun getHistoryAll(): List<SearchInput>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addHistory(searchInput: List<SearchInput>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addInput(searchInput: SearchInput)
}