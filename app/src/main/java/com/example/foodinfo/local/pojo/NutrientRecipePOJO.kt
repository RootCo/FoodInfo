package com.example.foodinfo.local.pojo

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.foodinfo.local.entity.NutrientFieldEntity
import com.example.foodinfo.local.entity.NutrientRecipeEntity


data class NutrientRecipePOJO(
    @PrimaryKey
    @ColumnInfo(name = NutrientRecipeEntity.Columns.ID)
    val id: Long,

    @ColumnInfo(name = NutrientRecipeEntity.Columns.NAME)
    val name: String,

    @ColumnInfo(name = NutrientRecipeEntity.Columns.RECIPE_ID)
    val recipeId: String,

    @ColumnInfo(name = NutrientRecipeEntity.Columns.TOTAL_VALUE)
    val totalValue: Double,

    @Relation(
        parentColumn = NutrientRecipeEntity.Columns.NAME,
        entityColumn = NutrientFieldEntity.Columns.NAME
    )
    val fieldInfo: NutrientFieldEntity
)