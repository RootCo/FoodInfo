package com.example.foodinfo.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.foodinfo.local.entity.RangeFieldEntity
import com.example.foodinfo.local.entity.SearchFilterEntity
import kotlinx.coroutines.flow.Flow


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

    @Query(
        "SELECT * FROM ${SearchFilterEntity.TABLE_NAME} " +
                "WHERE ${SearchFilterEntity.Columns.NAME} " +
                "LIKE '%' || :filterName || '%'"
    )
    fun getFilter(filterName: String): Flow<SearchFilterEntity>


    @Insert
    fun addRangeFields(fields: List<RangeFieldEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateFilter(filter: SearchFilterEntity)
}