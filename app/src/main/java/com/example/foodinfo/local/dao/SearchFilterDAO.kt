package com.example.foodinfo.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.foodinfo.local.entity.RangeFieldEntity


@Dao
interface SearchFilterDAO {

    @Query(
        "SELECT * FROM ${RangeFieldEntity.TABLE_NAME} " +
                "WHERE ${RangeFieldEntity.Columns.NAME} " +
                "LIKE '%' || :fieldName || '%'"
    )
    suspend fun getRangeField(fieldName: String): RangeFieldEntity

    @Query(
        "SELECT * FROM ${RangeFieldEntity.TABLE_NAME} " +
                "WHERE ${RangeFieldEntity.Columns.CATEGORY} " +
                "LIKE '%' || :categoryName || '%'"
    )
    suspend fun getRangeFieldsByCategory(categoryName: String): List<RangeFieldEntity>

    @Insert
    fun addRangeFields(fields: List<RangeFieldEntity>)
}