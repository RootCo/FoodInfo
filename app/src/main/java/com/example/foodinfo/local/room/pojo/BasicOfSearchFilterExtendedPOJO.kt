package com.example.foodinfo.local.room.pojo

import androidx.room.ColumnInfo
import androidx.room.Relation
import com.example.foodinfo.local.dto.BasicOfSearchFilterExtendedDB
import com.example.foodinfo.local.room.entity.BasicOfSearchFilterEntity
import com.example.foodinfo.local.room.entity.BasicRecipeAttrEntity


data class BasicOfSearchFilterExtendedPOJO(
    @ColumnInfo(name = BasicOfSearchFilterEntity.Columns.ID)
    override val ID: Int = 0,

    @ColumnInfo(name = BasicOfSearchFilterEntity.Columns.INFO_ID)
    override val infoID: Int,

    @ColumnInfo(name = BasicOfSearchFilterEntity.Columns.FILTER_NAME)
    override val filterName: String,

    @ColumnInfo(name = BasicOfSearchFilterEntity.Columns.MIN_VALUE)
    override val minValue: Float,

    @ColumnInfo(name = BasicOfSearchFilterEntity.Columns.MAX_VALUE)
    override val maxValue: Float,

    @Relation(
        parentColumn = BasicOfSearchFilterEntity.Columns.INFO_ID,
        entityColumn = BasicRecipeAttrEntity.Columns.ID
    )
    override val attrInfo: BasicRecipeAttrEntity

) : BasicOfSearchFilterExtendedDB(
    ID = ID,
    infoID = infoID,
    filterName = filterName,
    minValue = minValue,
    maxValue = maxValue,
    attrInfo = attrInfo
)