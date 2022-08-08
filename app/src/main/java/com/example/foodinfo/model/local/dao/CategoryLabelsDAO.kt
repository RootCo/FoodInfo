package com.example.foodinfo.model.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.foodinfo.model.local.entities.CategoryLabelEntity


@Dao
interface CategoryLabelsDAO {

    @Query(
        "${CategoryLabelEntity.SELECTOR} " +
                "WHERE ${CategoryLabelEntity.Columns.LABEL} " +
                "LIKE '%' || :label || '%' " +
                "AND ${CategoryLabelEntity.Columns.CATEGORY} " +
                "LIKE '%' || :category || '%'"
    )
    fun getByLabel(category: String, label: String): CategoryLabelEntity

    @Query(CategoryLabelEntity.SELECTOR)
    fun getAll(): List<CategoryLabelEntity>

    @Query(
        "${CategoryLabelEntity.SELECTOR} " +
                "WHERE ${CategoryLabelEntity.Columns.CATEGORY} " +
                "LIKE '%' || :category || '%'"
    )
    fun getCategory(category: String): List<CategoryLabelEntity>

    @Insert
    fun addCategory(category: List<CategoryLabelEntity>)
}