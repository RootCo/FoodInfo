package com.example.foodinfo.model.local.entities.recipe_field

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.foodinfo.model.local.entities.RecipeEntity


@Entity(
    tableName = RecipeMealLabelEntity.TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = RecipeEntity::class,
        parentColumns = arrayOf(RecipeEntity.Columns.ID),
        childColumns = arrayOf(RecipeMealLabelEntity.Columns.RECIPE_ID),
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
    )]
)
data class RecipeMealLabelEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Columns.ID)
    val id: Long,

    @ColumnInfo(name = Columns.RECIPE_ID)
    val recipeId: String,

    @ColumnInfo(name = Columns.LABEL)
    val label: String

) {
    object Columns {
        const val ID = "id"
        const val RECIPE_ID = "meal_recipe_id"
        const val LABEL = "label"
    }

    companion object {
        const val TABLE_NAME = "recipe_meal_labels"
        const val DISPLAY_NAME = "meal"
    }
}