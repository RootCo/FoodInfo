package com.example.foodinfo.model.local.entities

import androidx.room.ColumnInfo
import androidx.room.Relation
import com.example.foodinfo.model.local.entities.recipe_field.*


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
        entityColumn = RecipeMealLabelEntity.Columns.RECIPE_ID
    )
    val mealLabels: List<RecipeMealLabelEntity>,

    @Relation(
        parentColumn = RecipeEntity.Columns.ID,
        entityColumn = RecipeDishLabelEntity.Columns.RECIPE_ID
    )
    val dishLabels: List<RecipeDishLabelEntity>,

    @Relation(
        parentColumn = RecipeEntity.Columns.ID,
        entityColumn = RecipeDietLabelEntity.Columns.RECIPE_ID
    )
    val dietLabels: List<RecipeDietLabelEntity>,

    @Relation(
        parentColumn = RecipeEntity.Columns.ID,
        entityColumn = RecipeHealthLabelEntity.Columns.RECIPE_ID
    )
    val healthLabels: List<RecipeHealthLabelEntity>,

    @Relation(
        parentColumn = RecipeEntity.Columns.ID,
        entityColumn = RecipeCuisineLabelEntity.Columns.RECIPE_ID
    )
    val cuisineType: List<RecipeCuisineLabelEntity>,

    @Relation(
        parentColumn = RecipeEntity.Columns.ID,
        entityColumn = RecipeIngredientEntity.Columns.RECIPE_ID
    )
    val ingredients: List<RecipeIngredientEntity>,

    @Relation(
        parentColumn = RecipeEntity.Columns.ID,
        entityColumn = RecipeNutrientEntity.Columns.RECIPE_ID
    )
    val totalNutrients: List<RecipeNutrientEntity>,

    @Relation(
        parentColumn = RecipeEntity.Columns.ID,
        entityColumn = RecipeNutrientEntity.Columns.RECIPE_ID
    )
    val totalDaily: List<RecipeNutrientEntity>
) {

    companion object {
        const val SELECTOR = "SELECT * FROM ${RecipeEntity.TABLE_NAME}"
    }
}