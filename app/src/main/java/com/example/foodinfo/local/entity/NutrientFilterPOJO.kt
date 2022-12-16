package com.example.foodinfo.local.entity

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import androidx.room.Relation


data class NutrientFilterPOJO(
    @PrimaryKey
    @ColumnInfo(name = NutrientFilterEntity.Columns.ID)
    val id: Long,

    @ColumnInfo(name = NutrientFilterEntity.Columns.NAME)
    val name: String,

    @ColumnInfo(name = NutrientFilterEntity.Columns.FILTER_NAME)
    val filterName: String,

    @ColumnInfo(name = NutrientFilterEntity.Columns.MIN_VALUE)
    val minValue: Double,

    @ColumnInfo(name = NutrientFilterEntity.Columns.MAX_VALUE)
    val maxValue: Double,

    @Relation(
        parentColumn = NutrientRecipeEntity.Columns.NAME,
        entityColumn = NutrientFieldEntity.Columns.NAME
    )
    val nutrientFieldInfo: NutrientFieldEntity
)