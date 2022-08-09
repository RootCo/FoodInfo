package com.example.foodinfo.local.entity.recipe

import androidx.room.ColumnInfo
import androidx.room.Relation
import com.example.foodinfo.local.entity.RecipeIngredientEntity
import com.example.foodinfo.local.entity.RecipeLabelEntity
import com.example.foodinfo.local.entity.RecipeNutrientEntity


data class RecipeExtendedEntity(
    @ColumnInfo(name = RecipeEntity.Columns.ID)
    val id: String,

    @ColumnInfo(name = RecipeEntity.Columns.NAME)
    val name: String,

    @ColumnInfo(name = RecipeEntity.Columns.PREVIEW_URL)
    val previewURL: String,

    @ColumnInfo(name = RecipeEntity.Columns.CALORIES)
    val calories: Int,

    @ColumnInfo(name = RecipeEntity.Columns.PROTEIN)
    val protein: Int,

    @ColumnInfo(name = RecipeEntity.Columns.FAT)
    val fat: Int,

    @ColumnInfo(name = RecipeEntity.Columns.CARB)
    val carb: Int,

    @ColumnInfo(name = RecipeEntity.Columns.SOURCE)
    val source: String,

    @ColumnInfo(name = RecipeEntity.Columns.TOTAL_WEIGHT)
    val totalWeight: Int,

    @ColumnInfo(name = RecipeEntity.Columns.TOTAL_TIME)
    val totalTime: Int,

    @ColumnInfo(name = RecipeEntity.Columns.SERVINGS)
    val servings: Int,

    @Relation(
        parentColumn = RecipeEntity.Columns.ID,
        entityColumn = RecipeIngredientEntity.Columns.RECIPE_ID
    )
    val ingredients: List<RecipeIngredientEntity>,

    @Relation(
        parentColumn = RecipeEntity.Columns.ID,
        entityColumn = RecipeNutrientEntity.Columns.RECIPE_ID
    )
    val nutrients: List<RecipeNutrientEntity>,

    @Relation(
        parentColumn = RecipeEntity.Columns.ID,
        entityColumn = RecipeLabelEntity.Columns.RECIPE_ID
    )
    val labels: List<RecipeLabelEntity>

) {

    companion object {
        const val SELECTOR = "SELECT * FROM ${RecipeEntity.TABLE_NAME}"
    }
}