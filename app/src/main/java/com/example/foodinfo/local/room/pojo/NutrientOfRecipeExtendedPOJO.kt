package com.example.foodinfo.local.room.pojo

import androidx.room.ColumnInfo
import androidx.room.Relation
import com.example.foodinfo.local.dto.NutrientOfRecipeExtendedDB
import com.example.foodinfo.local.room.entity.NutrientOfRecipeEntity
import com.example.foodinfo.local.room.entity.NutrientRecipeAttrEntity


data class NutrientOfRecipeExtendedPOJO(
    @ColumnInfo(name = NutrientOfRecipeEntity.Columns.ID)
    override val ID: Int = 0,

    @ColumnInfo(name = NutrientOfRecipeEntity.Columns.RECIPE_ID)
    override val recipeID: String,

    @ColumnInfo(name = NutrientOfRecipeEntity.Columns.INFO_ID)
    override val infoID: Int,

    @ColumnInfo(name = NutrientOfRecipeEntity.Columns.TOTAL_VALUE)
    override val totalValue: Float,

    @Relation(
        parentColumn = NutrientOfRecipeEntity.Columns.INFO_ID,
        entityColumn = NutrientRecipeAttrEntity.Columns.ID
    )
    override val attrInfo: NutrientRecipeAttrEntity

) : NutrientOfRecipeExtendedDB(
    ID = ID,
    recipeID = recipeID,
    infoID = infoID,
    totalValue = totalValue,
    attrInfo = attrInfo
)