package com.example.foodinfo.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    tableName = RecipeIngredientEntity.TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = RecipeEntity::class,
        parentColumns = arrayOf(RecipeEntity.Columns.ID),
        childColumns = arrayOf(RecipeIngredientEntity.Columns.RECIPE_ID),
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
    )]
)
data class RecipeIngredientEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Columns.ID)
    val id: Long,

    @ColumnInfo(name = Columns.RECIPE_ID)
    val recipeId: String,

    @ColumnInfo(name = Columns.TEXT)
    val text: String,

    @ColumnInfo(name = Columns.QUANTITY)
    val quantity: Double,

    @ColumnInfo(name = Columns.MEASURE)
    val measure: String,

    @ColumnInfo(name = Columns.WEIGHT)
    val weight: Double,

    @ColumnInfo(name = Columns.FOOD)
    val food: String,

    @ColumnInfo(name = Columns.FOOD_CATEGORY)
    val foodCategory: String,

    @ColumnInfo(name = Columns.FOOD_ID)
    val foodId: String,

    @ColumnInfo(name = Columns.PREVIEW_URL)
    val previewURL: String
) {

    object Columns {
        const val ID = "id"
        const val RECIPE_ID = "ingredient_recipe_id"
        const val TEXT = "text"
        const val QUANTITY = "quantity"
        const val MEASURE = "measure"
        const val WEIGHT = "weight"
        const val FOOD = "food"
        const val FOOD_CATEGORY = "food_category"
        const val FOOD_ID = "food_id"
        const val PREVIEW_URL = "preview_url"

    }

    companion object {
        const val TABLE_NAME = "recipe_ingredients"
    }
}