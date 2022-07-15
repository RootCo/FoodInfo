package com.example.foodinfo.model.local.entities.recipe_field

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.foodinfo.model.local.entities.Recipe


@Entity(
    tableName = HealthType.TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = Recipe::class,
        parentColumns = arrayOf(Recipe.Columns.ID),
        childColumns = arrayOf(HealthType.Columns.RECIPE_ID),
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
    )]
)
data class HealthType(
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
        const val RECIPE_ID = "health_recipe_id"
        const val LABEL = "label"
    }

    companion object {
        const val TABLE_NAME = "health_type"
        val validLabels = listOf(
            "vegan",
            "vegetarian",
            "paleo",
            "dairy-free",
            "gluten-free",
            "wheat-free",
            "fat-free",
            "low-sugar",
            "egg-free",
            "peanut-free",
            "tree-nut-free",
            "soy-free",
            "fish-free",
            "shellfish-free"
        )
    }
}