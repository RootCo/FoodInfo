package com.example.foodinfo.local.pojo

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.example.foodinfo.local.entity.RecipeEntity


// TODO this class do longer needed due to favorite mark is no longer a separate table
data class RecipePOJO(
    @PrimaryKey
    @ColumnInfo(name = RecipeEntity.Columns.ID)
    val id: String,

    @ColumnInfo(name = RecipeEntity.Columns.SOURCE)
    val source: String,

    @ColumnInfo(name = RecipeEntity.Columns.NAME)
    val name: String,

    @ColumnInfo(name = RecipeEntity.Columns.PREVIEW_URL)
    val previewURL: String,

    @ColumnInfo(name = RecipeEntity.Columns.CALORIES)
    val calories: Int,

    @ColumnInfo(name = RecipeEntity.Columns.INGREDIENTS_COUNT)
    val ingredientsCount: Int,

    @ColumnInfo(name = RecipeEntity.Columns.WEIGHT)
    val weight: Int,

    @ColumnInfo(name = RecipeEntity.Columns.COOKING_TIME)
    val cookingTime: Int,

    @ColumnInfo(name = RecipeEntity.Columns.SERVINGS)
    val servings: Int,

    @ColumnInfo(name = RecipeEntity.Columns.IS_FAVORITE)
    val isFavorite: Boolean
)