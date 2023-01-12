package com.example.foodinfo.local.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    tableName = NutrientRecipeEntity.TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = RecipeEntity::class,
        parentColumns = arrayOf(RecipeEntity.Columns.ID),
        childColumns = arrayOf(NutrientRecipeEntity.Columns.RECIPE_ID),
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
    )]
)
data class NutrientRecipeEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Columns.ID)
    val id: Long = 0,

    @ColumnInfo(name = Columns.RECIPE_ID)
    val recipeId: String,

    @Embedded
    val nutrient: NutrientFieldEntity,

    @ColumnInfo(name = Columns.TOTAL_VALUE)
    val totalValue: Float,

    @ColumnInfo(name = Columns.DAILY_VALUE)
    val dailyValue: Float
) {
    object Columns {
        const val ID = "id"
        const val RECIPE_ID = "nutrient_recipe_id"
        const val TOTAL_VALUE = "total_value"
        const val DAILY_VALUE = "daily_value"
    }

    companion object {
        const val TABLE_NAME = "recipe_nutrients"
    }
}