package com.example.foodinfo.model.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.foodinfo.model.local.entities.SearchInputEntity


@Dao
interface SearchHistoryDAO {
    @Query(
        "${SearchInputEntity.SELECTOR} " +
                "WHERE ${SearchInputEntity.Columns.INPUT_TEXT} " +
                "LIKE '%' || :inputText || '%' " +
                "ORDER BY ${SearchInputEntity.Columns.DATE} DESC " +
                "LIMIT ${SearchInputEntity.LIMIT}"
    )
    fun getHistoryLatest(inputText: String): List<SearchInputEntity>

    @Query("${SearchInputEntity.SELECTOR} ORDER BY ${SearchInputEntity.Columns.DATE}")
    fun getHistoryAll(): List<SearchInputEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addHistory(searchInput: List<SearchInputEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addInput(searchInput: SearchInputEntity)
}