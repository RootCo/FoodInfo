package com.example.foodinfo.local.room.pojo

import androidx.room.ColumnInfo
import androidx.room.Relation
import com.example.foodinfo.local.dto.LabelRecipeAttrExtendedDB
import com.example.foodinfo.local.room.entity.CategoryRecipeAttrEntity
import com.example.foodinfo.local.room.entity.LabelRecipeAttrEntity


data class LabelRecipeAttrExtendedPOJO(
    @ColumnInfo(name = LabelRecipeAttrEntity.Columns.ID)
    override val ID: Int,

    @ColumnInfo(name = LabelRecipeAttrEntity.Columns.CATEGORY_ID)
    override val categoryID: Int,

    @ColumnInfo(name = LabelRecipeAttrEntity.Columns.TAG)
    override val tag: String,

    @ColumnInfo(name = LabelRecipeAttrEntity.Columns.NAME)
    override val name: String,

    @ColumnInfo(name = LabelRecipeAttrEntity.Columns.DESCRIPTION)
    override val description: String,

    @ColumnInfo(name = LabelRecipeAttrEntity.Columns.PREVIEW_URL)
    override val previewURL: String,

    @Relation(
        parentColumn = LabelRecipeAttrEntity.Columns.CATEGORY_ID,
        entityColumn = CategoryRecipeAttrEntity.Columns.ID,
    )
    override val categoryInfo: CategoryRecipeAttrEntity

) : LabelRecipeAttrExtendedDB(
    ID = ID,
    categoryID = categoryID,
    tag = tag,
    name = name,
    description = description,
    previewURL = previewURL,
    categoryInfo = categoryInfo
)