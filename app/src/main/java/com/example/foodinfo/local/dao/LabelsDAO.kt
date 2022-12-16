package com.example.foodinfo.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.foodinfo.local.entity.CategoryEntity
import com.example.foodinfo.local.entity.LabelEntity


/*
    not sure whether i should split labels and categories into 2 separate DAO or not
    (in case they are too small and kinda same)

    no Flow here because usually it uses just to once grab data from database even though
    data comes from internet.
    labels/categories DAO and Repo might be simply replaced by json file or even hardcoded
    into lists but it will lead to app updates each time developers decides to change
    description or icon for label or add|replace category

 */

@Dao
interface LabelsDAO {

    @Query(
        "SELECT * FROM ${LabelEntity.TABLE_NAME} " +
                "WHERE ${LabelEntity.Columns.NAME} " +
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