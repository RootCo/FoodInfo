package com.example.foodinfo.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.foodinfo.local.dto.RecipeDB


@Entity(
    tableName = RecipeEntity.TABLE_NAME,
    indices = [Index(value = arrayOf(RecipeEntity.Columns.ID), unique = true)]
)
data class RecipeEntity(
    @PrimaryKey
    @ColumnInfo(name = Columns.ID)
    override val ID: String,

    @ColumnInfo(name = Columns.SOURCE)
    override val source: String,

    @ColumnInfo(name = Columns.NAME)
    override val name: String,

    @ColumnInfo(name = Columns.PREVIEW_URL)
    override val previewURL: String,

    @ColumnInfo(name = Columns.CALORIES)
    override val calories: Int,

    @ColumnInfo(name = Columns.INGREDIENTS_COUNT)
    override val ingredientsCount: Int,

    @ColumnInfo(name = Columns.WEIGHT)
    override val weight: Int,

    @ColumnInfo(name = Columns.COOKING_TIME)
    override val cookingTime: Int,

    @ColumnInfo(name = Columns.SERVINGS)
    override val servings: Int,

    @ColumnInfo(name = Columns.IS_FAVORITE)
    override val isFavorite: Boolean = false

) : RecipeDB(
    ID = ID,
    source = source,
    name = name,
    previewURL = previewURL,
    calories = calories,
    ingredientsCount = ingredientsCount,
    weight = weight,
    cookingTime = cookingTime,
    servings = servings,
    isFavorite = isFavorite
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

        fun fromDB(item: RecipeDB): RecipeEntity {
            return RecipeEntity(
                ID = item.ID,
                source = item.source,
                name = item.name,
                previewURL = item.previewURL,
                calories = item.calories,
                ingredientsCount = item.ingredientsCount,
                weight = item.weight,
                cookingTime = item.cookingTime,
                servings = item.servings,
                isFavorite = item.isFavorite
            )
        }
    }
}