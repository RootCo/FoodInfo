package com.example.foodinfo.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.foodinfo.local.entity.LabelEntity


@Dao
interface LabelsDAO {

    @Query(
        "SELECT * FROM ${LabelEntity.TABLE_NAME} " +
                "WHERE ${LabelEntity.Columns.LABEL} " +
                "LIKE '%' || :label || '%' " +
                "AND ${LabelEntity.Columns.CATEGORY} " +
                "LIKE '%' || :category || '%'"
    )
    fun getByLabel(category: String, label: String): LabelEntity

    @Query("SELECT * FROM ${LabelEntity.TABLE_NAME}")
    fun getAll(): List<LabelEntity>

    @Query(
        "SELECT * FROM ${LabelEntity.TABLE_NAME} " +
                "WHERE ${LabelEntity.Columns.CATEGORY} " +
                "LIKE '%' || :category || '%'"
    )
    fun getCategory(category: String): List<LabelEntity>

    @Insert
    fun addCategory(category: List<LabelEntity>)
}