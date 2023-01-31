package com.example.foodinfo.local.room.pojo

import androidx.room.ColumnInfo
import androidx.room.Relation
import com.example.foodinfo.local.dto.LabelOfRecipeExtendedDB
import com.example.foodinfo.local.room.entity.LabelOfRecipeEntity
import com.example.foodinfo.local.room.entity.LabelRecipeAttrEntity


data class LabelOfRecipeExtendedPOJO(
    @ColumnInfo(name = LabelOfRecipeEntity.Columns.ID)
    override val ID: Int = 0,

    @ColumnInfo(name = LabelOfRecipeEntity.Columns.RECIPE_ID)
    override val recipeID: String,

    @ColumnInfo(name = LabelOfRecipeEntity.Columns.INFO_ID)
    override val infoID: Int,

    @Relation(
        parentColumn = LabelOfRecipeEntity.Columns.INFO_ID,
        entityColumn = LabelRecipeAttrEntity.Columns.ID,
        entity = LabelRecipeAttrEntity::class
    )
    override val attrInfo: LabelRecipeAttrExtendedPOJO

) : LabelOfRecipeExtendedDB(
    ID = ID,
    recipeID = recipeID,
    infoID = infoID,
    attrInfo = attrInfo
)