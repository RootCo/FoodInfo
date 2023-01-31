package com.example.foodinfo.local.room.pojo

import androidx.room.ColumnInfo
import androidx.room.Relation
import com.example.foodinfo.local.dto.NutrientOfSearchFilterExtendedDB
import com.example.foodinfo.local.room.entity.NutrientOfSearchFilterEntity
import com.example.foodinfo.local.room.entity.NutrientRecipeAttrEntity


data class NutrientOfSearchFilterExtendedPOJO(
    @ColumnInfo(name = NutrientOfSearchFilterEntity.Columns.ID)
    override val ID: Int = 0,

    @ColumnInfo(name = NutrientOfSearchFilterEntity.Columns.FILTER_NAME)
    override val filterName: String,

    @ColumnInfo(name = NutrientOfSearchFilterEntity.Columns.INFO_ID)
    override val infoID: Int,

    @ColumnInfo(name = NutrientOfSearchFilterEntity.Columns.MIN_VALUE)
    override val minValue: Float,

    @ColumnInfo(name = NutrientOfSearchFilterEntity.Columns.MAX_VALUE)
    override val maxValue: Float,

    @Relation(
        parentColumn = NutrientOfSearchFilterEntity.Columns.INFO_ID,
        entityColumn = NutrientRecipeAttrEntity.Columns.ID
    )
    override val attrInfo: NutrientRecipeAttrEntity

) : NutrientOfSearchFilterExtendedDB(
    ID = ID,
    filterName = filterName,
    infoID = infoID,
    minValue = minValue,
    maxValue = maxValue,
    attrInfo = attrInfo
)
