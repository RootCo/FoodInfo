package com.example.foodinfo.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.foodinfo.local.entity.BaseFieldEntity
import com.example.foodinfo.local.entity.CategoryFieldEntity
import com.example.foodinfo.local.entity.LabelFieldEntity
import com.example.foodinfo.local.entity.NutrientFieldEntity


@Dao
interface RecipeFieldsInfoDao {

    @Query(
        "SELECT * FROM ${NutrientFieldEntity.TABLE_NAME} " +
                "WHERE ${NutrientFieldEntity.Columns.NAME} " +
                "LIKE '%' || :nutrientName || '%'"
    )
    fun getNutrient(nutrientName: String): NutrientFieldEntity


    @Query(
        "SELECT * FROM ${LabelFieldEntity.TABLE_NAME} " +
                "WHERE ${LabelFieldEntity.Columns.NAME} " +
                "LIKE '%' || :labelName || '%' " +
                "AND ${LabelFieldEntity.Columns.CATEGORY} " +
                "LIKE '%' || :categoryName || '%'"
    )
    fun getLabel(categoryName: String, labelName: String): LabelFieldEntity

    @Query(
        "SELECT * FROM ${LabelFieldEntity.TABLE_NAME} " +
                "WHERE ${LabelFieldEntity.Columns.CATEGORY} " +
                "LIKE '%' || :categoryName || '%'"
    )
    fun getLabels(categoryName: String): List<LabelFieldEntity>


    @Query(
        "SELECT * FROM ${CategoryFieldEntity.TABLE_NAME} " +
                "WHERE ${CategoryFieldEntity.Columns.NAME} " +
                "LIKE '%' || :name || '%'"
    )
    fun getCategory(name: String): CategoryFieldEntity

    @Query("SELECT * FROM ${CategoryFieldEntity.TABLE_NAME}")
    fun getCategories(): List<CategoryFieldEntity>


    @Insert
    fun addNutrientFields(fields: List<NutrientFieldEntity>)

    @Insert
    fun addBaseFields(fields: List<BaseFieldEntity>)

    @Insert
    fun addLabelsFields(labels: List<LabelFieldEntity>)

    @Insert
    fun addCategoriesFields(categories: List<CategoryFieldEntity>)


    @Query("SELECT * FROM ${NutrientFieldEntity.TABLE_NAME}")
    fun getNutrientFields(): List<NutrientFieldEntity>

    @Query("SELECT * FROM ${BaseFieldEntity.TABLE_NAME}")
    fun getBaseFields(): List<BaseFieldEntity>

    @Query("SELECT * FROM ${LabelFieldEntity.TABLE_NAME}")
    fun getLabelFields(): List<LabelFieldEntity>
}