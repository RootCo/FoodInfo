package com.example.foodinfo.model.local

import androidx.room.ColumnInfo
import com.example.foodinfo.model.local.entities.Recipe


data class RecipeShortDTO(
    @ColumnInfo(name = Recipe.Columns.ID)
    val id: String,

    @ColumnInfo(name = Recipe.Columns.NAME)
    val name: String,

    @ColumnInfo(name = Recipe.Columns.CALORIES)
    val calories: Int,

    @ColumnInfo(name = Recipe.Columns.SERVINGS)
    val servings: Int,

    @ColumnInfo(name = Recipe.Columns.TOTAL_TIME)
    val totalTime: Int,

    @ColumnInfo(name = Recipe.Columns.TOTAL_INGREDIENTS)
    val totalIngredients: Int,

    @ColumnInfo(name = Recipe.Columns.PREVIEW_URL)
    val previewURL: String,
) {

    companion object {
        const val SELECTOR =
            "SELECT " +
                    "${Recipe.Columns.ID}, " +
                    "${Recipe.Columns.NAME}, " +
                    "${Recipe.Columns.CALORIES}, " +
                    "${Recipe.Columns.SERVINGS}, " +
                    "${Recipe.Columns.TOTAL_TIME}, " +
                    "${Recipe.Columns.TOTAL_INGREDIENTS}, " +
                    "${Recipe.Columns.PREVIEW_URL} " +
                    "FROM ${Recipe.TABLE_NAME}"
    }
}