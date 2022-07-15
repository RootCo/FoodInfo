package com.example.foodinfo.model.local.dao

import androidx.room.*
import com.example.foodinfo.model.local.entities.SearchFilter


@Dao
interface SearchFilterDAO {
    @Query("${SearchFilter.SELECTOR} WHERE ${SearchFilter.Columns.NAME} = \"target\"")
    fun getTarget(): SearchFilter

    @Query("DELETE FROM ${SearchFilter.TABLE_NAME} WHERE ${SearchFilter.Columns.ID} = \"target\"")
    fun delTarget()

    @Query("${SearchFilter.SELECTOR} WHERE ${SearchFilter.Columns.ID} = :id")
    fun getFromPreset(id: Long): SearchFilter

    @Query("${SearchFilter.SELECTOR} WHERE ${SearchFilter.Columns.NAME} != \"target\"")
    fun getAllFromPreset(): List<SearchFilter>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addToPreset(filter: SearchFilter)

    @Query("DELETE FROM ${SearchFilter.TABLE_NAME} WHERE ${SearchFilter.Columns.ID} = :id")
    fun delFromPreset(id: Long)
}