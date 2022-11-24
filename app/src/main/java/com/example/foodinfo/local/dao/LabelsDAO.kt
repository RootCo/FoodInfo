package com.example.foodinfo.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.foodinfo.local.entity.CategoryEntity
import com.example.foodinfo.local.entity.LabelEntity


@Dao
interface LabelsDAO {

    @Query(
        "SELECT * FROM ${LabelEntity.TABLE_NAME} " +
                "WHERE ${LabelEntity.Columns.LABEL} " +
                "LIKE '%' || :labelName || '%' " +
                "AND ${LabelEntity.Columns.CATEGORY} " +
                "LIKE '%' || :categoryName || '%'"
    )
    fun getLabel(categoryName: String, labelName: String): LabelEntity

    @Query(
        "SELECT * FROM ${LabelEntity.TABLE_NAME} " +
                "WHERE ${LabelEntity.Columns.CATEGORY} " +
                "LIKE '%' || :categoryName || '%'"
    )
    fun getLabels(categoryName: String): List<LabelEntity>


    @Query(
        "SELECT * FROM ${CategoryEntity.TABLE_NAME} " +
                "WHERE ${CategoryEntity.Columns.NAME} " +
                "LIKE '%' || :name || '%'"
    )
    fun getCategory(name: String): CategoryEntity

    @Query("SELECT * FROM ${CategoryEntity.TABLE_NAME}")
    fun getCategories(): List<CategoryEntity>


    @Insert
    fun addCategoryLabels(labels: List<LabelEntity>)

    @Insert
    fun addCategories(categories: List<CategoryEntity>)
}