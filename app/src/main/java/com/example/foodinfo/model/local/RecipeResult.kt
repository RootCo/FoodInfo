package com.example.foodinfo.model.local

import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.DiffUtil
import androidx.room.ColumnInfo
import androidx.room.Ignore
import com.example.foodinfo.model.local.entities.Recipe


data class RecipeResult(
    @ColumnInfo(name = Recipe.Columns.ID)
    val id: String,

    @ColumnInfo(name = Recipe.Columns.NAME)
    val name: String,

    @ColumnInfo(name = Recipe.Columns.CALORIES)
    val fat: Int,

    @ColumnInfo(name = Recipe.Columns.FAT)
    val carb: Int,

    @ColumnInfo(name = Recipe.Columns.CARB)
    val protein: Int,

    @ColumnInfo(name = Recipe.Columns.PROTEIN)
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
    @Ignore
    var preview: Drawable? = null

    object ItemCallBack : DiffUtil.ItemCallback<RecipeResult>() {
        override fun areItemsTheSame(
            oldItem: RecipeResult, newItem: RecipeResult
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: RecipeResult, newItem: RecipeResult
        ): Boolean {
            return oldItem.name == newItem.name &&
                    oldItem.calories == newItem.calories &&
                    oldItem.servings == newItem.servings &&
                    oldItem.totalTime == newItem.totalTime &&
                    oldItem.totalIngredients == newItem.totalIngredients &&
                    oldItem.fat == newItem.fat &&
                    oldItem.carb == newItem.carb &&
                    oldItem.protein == newItem.protein

        }
    }

    companion object {
        const val SELECTOR =
            "SELECT " +
                    "${Recipe.Columns.ID}, " +
                    "${Recipe.Columns.NAME}, " +
                    "${Recipe.Columns.FAT}, " +
                    "${Recipe.Columns.CARB}, " +
                    "${Recipe.Columns.PROTEIN}, " +
                    "${Recipe.Columns.CALORIES}, " +
                    "${Recipe.Columns.SERVINGS}, " +
                    "${Recipe.Columns.TOTAL_TIME}, " +
                    "${Recipe.Columns.TOTAL_INGREDIENTS}, " +
                    "${Recipe.Columns.PREVIEW_URL} " +
                    "FROM ${Recipe.TABLE_NAME}"
    }
}