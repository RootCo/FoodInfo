package com.example.foodinfo.local.entity.recipe

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
    tableName = RecipeEntity.TABLE_NAME,
    indices = [Index(value = arrayOf(RecipeEntity.Columns.ID), unique = true)]
)
data class RecipeEntity(
    @PrimaryKey
    @ColumnInfo(name = Columns.ID)
    val id: String,

    @ColumnInfo(name = Columns.SOURCE)
    val source: String,

    @ColumnInfo(name = Columns.NAME)
    val name: String,

    @ColumnInfo(name = Columns.PREVIEW_URL)
    val previewURL: String,

    @ColumnInfo(name = Columns.CALORIES)
    val calories: Int,

    @ColumnInfo(name = Columns.PROTEIN)
    val protein: Int,

    @ColumnInfo(name = Columns.CARB)
    val carb: Int,

    @ColumnInfo(name = Columns.FAT)
    val fat: Int,

    @ColumnInfo(name = Columns.TOTAL_INGREDIENTS)
    val totalIngredients: Int,

    @ColumnInfo(name = Columns.TOTAL_WEIGHT)
    val totalWeight: Int,

    @ColumnInfo(name = Columns.TOTAL_TIME)
    val totalTime: Int,

    @ColumnInfo(name = Columns.SERVINGS)
    val servings: Int,
) {

    object Columns {
        const val ID = "id"
        const val SOURCE = "source"
        const val NAME = "name"
        const val PREVIEW_URL = "preview_url"
        const val CALORIES = "calories"
        const val PROTEIN = "protein"
        const val CARB = "carb"
        const val FAT = "fat"
        const val TOTAL_INGREDIENTS = "total_ingredient"
        const val TOTAL_WEIGHT = "total_weight"
        const val TOTAL_TIME = "total_time"
        const val SERVINGS = "servings"
    }

    companion object {
        const val TABLE_NAME = "recipe"
        const val SELECTOR = "SELECT * FROM $TABLE_NAME"
    }
}