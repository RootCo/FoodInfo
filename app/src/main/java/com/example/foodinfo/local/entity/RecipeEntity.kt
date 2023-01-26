package com.example.foodinfo.local.entity

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

    @ColumnInfo(name = Columns.INGREDIENTS_COUNT)
    val ingredientsCount: Int,

    @ColumnInfo(name = Columns.WEIGHT)
    val weight: Int,

    @ColumnInfo(name = Columns.COOKING_TIME)
    val cookingTime: Int,

    @ColumnInfo(name = Columns.SERVINGS)
    val servings: Int,

    @ColumnInfo(name = Columns.IS_FAVORITE)
    val isFavorite: Boolean = false
) {

    object Columns {
        const val ID = "id"
        const val SOURCE = "source"
        const val NAME = "name"
        const val PREVIEW_URL = "preview_url"
        const val CALORIES = "calories"
        const val INGREDIENTS_COUNT = "ingredients"
        const val WEIGHT = "weight"
        const val COOKING_TIME = "time"
        const val SERVINGS = "servings"
        const val IS_FAVORITE = "is_favorite"
    }

    companion object {
        const val TABLE_NAME = "recipe"
    }
}