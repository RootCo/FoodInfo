package com.example.foodinfo.local.pojo

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.foodinfo.local.entity.FavoriteMarkEntity
import com.example.foodinfo.local.entity.RecipeEntity


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

    @ColumnInfo(name = RecipeEntity.Columns.TOTAL_INGREDIENTS)
    val totalIngredients: Int,

    @ColumnInfo(name = RecipeEntity.Columns.TOTAL_WEIGHT)
    val totalWeight: Int,

    @ColumnInfo(name = RecipeEntity.Columns.TOTAL_TIME)
    val totalTime: Int,

    @ColumnInfo(name = RecipeEntity.Columns.SERVINGS)
    val servings: Int,

    @Relation(
        parentColumn = RecipeEntity.Columns.ID,
        entityColumn = FavoriteMarkEntity.Columns.RECIPE_ID
    )
    val favoriteMark: FavoriteMarkEntity
)