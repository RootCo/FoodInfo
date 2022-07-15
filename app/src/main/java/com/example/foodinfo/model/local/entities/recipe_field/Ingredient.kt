package com.example.foodinfo.model.local.entities.recipe_field

import android.graphics.drawable.Drawable
import androidx.room.*
import com.example.foodinfo.model.local.entities.Recipe


@Entity(
    tableName = Ingredient.TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = Recipe::class,
        parentColumns = arrayOf(Recipe.Columns.ID),
        childColumns = arrayOf(Ingredient.Columns.RECIPE_ID),
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
    )]
)
data class Ingredient(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Columns.ID)
    val id: Long,

    @ColumnInfo(name = Columns.RECIPE_ID)
    val recipeId: String,

    @ColumnInfo(name = Columns.TEXT)
    val text: String,

    @ColumnInfo(name = Columns.QUANTITY)
    val quantity: Float,

    @ColumnInfo(name = Columns.MEASURE)
    val measure: String,

    @ColumnInfo(name = Columns.FOOD)
    val food: String,

    @ColumnInfo(name = Columns.WEIGHT)
    val weight: String,

    @ColumnInfo(name = Columns.FOOD_CATEGORY)
    val foodCategory: String,

    @ColumnInfo(name = Columns.FOOD_ID)
    val foodId: String,

    @ColumnInfo(name = Columns.PREVIEW_URL)
    val previewURL: String
) {

    @Ignore
    val preview: Drawable? = null

    object Columns {
        const val ID = "id"
        const val RECIPE_ID = "ingredient_recipe_id"
        const val TEXT = "text"
        const val QUANTITY = "quantity"
        const val MEASURE = "measure"
        const val FOOD = "food"
        const val WEIGHT = "weight"
        const val FOOD_CATEGORY = "food_category"
        const val FOOD_ID = "food_id"
        const val PREVIEW_URL = "preview_url"

    }

    companion object {
        const val TABLE_NAME = "ingredient"
        const val SELECTOR = "SELECT * FROM $TABLE_NAME"
    }
}