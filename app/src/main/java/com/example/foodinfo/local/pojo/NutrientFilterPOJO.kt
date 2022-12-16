package com.example.foodinfo.local.pojo

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.foodinfo.local.entity.NutrientFieldEntity
import com.example.foodinfo.local.entity.NutrientFilterEntity
import com.example.foodinfo.local.entity.NutrientRecipeEntity


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
    val fieldInfo: NutrientFieldEntity
)