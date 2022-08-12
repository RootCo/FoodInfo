package com.example.foodinfo.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.foodinfo.local.entity.NutrientEntity


@Dao
interface NutrientsDAO {

    @Query(
        "SELECT * FROM ${NutrientEntity.TABLE_NAME} " +
                "WHERE ${NutrientEntity.Columns.LABEL} " +
                "LIKE '%' || :label || '%'"
    )
    fun getByLabel(label: String): NutrientEntity

    @Query("SELECT * FROM ${NutrientEntity.TABLE_NAME}")
    fun getAll(): List<NutrientEntity>

    @Insert
    fun addAll(nutrients: List<NutrientEntity>)
}