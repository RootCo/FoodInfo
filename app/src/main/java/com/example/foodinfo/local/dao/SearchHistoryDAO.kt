package com.example.foodinfo.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.foodinfo.local.entity.SearchInputEntity


@Dao
interface SearchHistoryDAO {
    @Query(
        "SELECT * FROM ${SearchInputEntity.TABLE_NAME} " +
                "WHERE ${SearchInputEntity.Columns.INPUT_TEXT} " +
                "LIKE '%' || :inputText || '%' " +
                "ORDER BY ${SearchInputEntity.Columns.DATE} DESC " +
                "LIMIT ${SearchInputEntity.LIMIT}"
    )
    fun getHistoryLatest(inputText: String): List<SearchInputEntity>

    @Query(
        "SELECT * FROM ${SearchInputEntity.TABLE_NAME} " +
                "ORDER BY ${SearchInputEntity.Columns.DATE}"
    )
    fun getHistoryAll(): List<SearchInputEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addHistory(searchInput: List<SearchInputEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addInput(searchInput: SearchInputEntity)
}