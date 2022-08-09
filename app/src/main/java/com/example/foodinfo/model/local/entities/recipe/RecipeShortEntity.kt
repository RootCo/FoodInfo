package com.example.foodinfo.model.local.entities.recipe

import androidx.room.ColumnInfo


data class RecipeShortEntity(
    @ColumnInfo(name = RecipeEntity.Columns.ID)
    val id: String,

    @ColumnInfo(name = RecipeEntity.Columns.NAME)
    val name: String,

    @ColumnInfo(name = RecipeEntity.Columns.CALORIES)
    val calories: Int,

    @ColumnInfo(name = RecipeEntity.Columns.SERVINGS)
    val servings: Int,

    @ColumnInfo(name = RecipeEntity.Columns.TOTAL_TIME)
    val totalTime: Int,

    @ColumnInfo(name = RecipeEntity.Columns.TOTAL_INGREDIENTS)
    val totalIngredients: Int,

    @ColumnInfo(name = RecipeEntity.Columns.PREVIEW_URL)
    val previewURL: String,
) {

    companion object {
        const val SELECTOR =
            "SELECT " +
                    "${RecipeEntity.Columns.ID}, " +
                    "${RecipeEntity.Columns.NAME}, " +
                    "${RecipeEntity.Columns.CALORIES}, " +
                    "${RecipeEntity.Columns.SERVINGS}, " +
                    "${RecipeEntity.Columns.TOTAL_TIME}, " +
                    "${RecipeEntity.Columns.TOTAL_INGREDIENTS}, " +
                    "${RecipeEntity.Columns.PREVIEW_URL} " +
                    "FROM ${RecipeEntity.TABLE_NAME}"
    }
}