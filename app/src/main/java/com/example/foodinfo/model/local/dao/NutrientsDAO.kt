package com.example.foodinfo.model.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.foodinfo.model.local.entities.NutrientEntity


@Dao
interface NutrientsDAO {

    @Query(
        "${NutrientEntity.SELECTOR} " +
                "WHERE ${NutrientEntity.Columns.LABEL} " +
                "LIKE '%' || :label || '%'"
    )
    fun getByLabel(label: String): NutrientEntity

    @Query(NutrientEntity.SELECTOR)
    fun getAll(): List<NutrientEntity>

    @Insert
    fun addAll(nutrients: List<NutrientEntity>)
}