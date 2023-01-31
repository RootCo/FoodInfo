package com.example.foodinfo.local.room.pojo

import androidx.room.ColumnInfo
import androidx.room.Relation
import com.example.foodinfo.local.dto.LabelOfSearchFilterExtendedDB
import com.example.foodinfo.local.room.entity.LabelOfRecipeEntity
import com.example.foodinfo.local.room.entity.LabelOfSearchFilterEntity
import com.example.foodinfo.local.room.entity.LabelRecipeAttrEntity


data class LabelOfSearchFilterExtendedPOJO(
    @ColumnInfo(name = LabelOfSearchFilterEntity.Columns.ID)
    override val ID: Int = 0,

    @ColumnInfo(name = LabelOfSearchFilterEntity.Columns.FILTER_NAME)
    override val filterName: String,

    @ColumnInfo(name = LabelOfSearchFilterEntity.Columns.INFO_ID)
    override val infoID: Int,

    @ColumnInfo(name = LabelOfSearchFilterEntity.Columns.IS_SELECTED)
    override val isSelected: Boolean,

    @Relation(
        parentColumn = LabelOfRecipeEntity.Columns.INFO_ID,
        entityColumn = LabelRecipeAttrEntity.Columns.ID,
        entity = LabelRecipeAttrEntity::class
    )
    override val attrInfo: LabelRecipeAttrExtendedPOJO

) : LabelOfSearchFilterExtendedDB(
    ID = ID,
    filterName = filterName,
    infoID = infoID,
    isSelected = isSelected,
    attrInfo = attrInfo
)