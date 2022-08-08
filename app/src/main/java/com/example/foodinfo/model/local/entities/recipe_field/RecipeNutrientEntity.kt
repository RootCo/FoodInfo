package com.example.foodinfo.model.local.entities.recipe_field

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.foodinfo.model.local.entities.RecipeEntity


@Entity(
    tableName = RecipeNutrientEntity.TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = RecipeEntity::class,
        parentColumns = arrayOf(RecipeEntity.Columns.ID),
        childColumns = arrayOf(RecipeNutrientEntity.Columns.RECIPE_ID),
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
    )]
)
data class RecipeNutrientEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Columns.ID)
    val id: Long,

    @ColumnInfo(name = Columns.RECIPE_ID)
    val recipeId: String,

    @ColumnInfo(name = Columns.LABEL)
    val label: String,

    @ColumnInfo(name = Columns.TOTAL_VALUE)
    val totalValue: Float,

    @ColumnInfo(name = Columns.DAILY_VALUE)
    val dailyValue: Float,

    @ColumnInfo(name = Columns.UNIT)
    val unit: String
) {
    object Columns {
        const val ID = "id"
        const val RECIPE_ID = "nutrient_recipe_id"
        const val LABEL = "label"
        const val TOTAL_VALUE = "total_value"
        const val DAILY_VALUE = "daily_value"
        const val UNIT = "unit"
    }

    companion object {
        const val TABLE_NAME = "recipe_nutrients"
        const val SELECTOR = "SELECT * FROM $TABLE_NAME"
    }
}