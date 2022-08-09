package com.example.foodinfo.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.foodinfo.local.entity.LabelEntity


@Dao
interface LabelsDAO {

    @Query(
        "${LabelEntity.SELECTOR} " +
                "WHERE ${LabelEntity.Columns.LABEL} " +
                "LIKE '%' || :label || '%' " +
                "AND ${LabelEntity.Columns.CATEGORY} " +
                "LIKE '%' || :category || '%'"
    )
    fun getByLabel(category: String, label: String): LabelEntity

    @Query(LabelEntity.SELECTOR)
    fun getAll(): List<LabelEntity>

    @Query(
        "${LabelEntity.SELECTOR} " +
                "WHERE ${LabelEntity.Columns.CATEGORY} " +
                "LIKE '%' || :category || '%'"
    )
    fun getCategory(category: String): List<LabelEntity>

    @Insert
    fun addCategory(category: List<LabelEntity>)
}