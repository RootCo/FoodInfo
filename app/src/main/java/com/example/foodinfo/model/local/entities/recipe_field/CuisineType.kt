package com.example.foodinfo.model.local.entities.recipe_field

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.foodinfo.model.local.entities.Recipe


@Entity(
    tableName = CuisineType.TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = Recipe::class,
        parentColumns = arrayOf(Recipe.Columns.ID),
        childColumns = arrayOf(CuisineType.Columns.RECIPE_ID),
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
    )]
)
data class CuisineType(
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
        const val RECIPE_ID = "cuisine_recipe_id"
        const val LABEL = "label"
    }

    companion object {
        const val TABLE_NAME = "cuisine_type"
        val validLabels = listOf(
            "American",
            "Asian",
            "British",
            "Caribbean",
            "Central Europe",
            "Chinese",
            "Eastern Europe",
            "French",
            "Indian",
            "Italian",
            "Japanese",
            "Kosher",
            "Mediterranean",
            "Mexican",
            "Middle Eastern",
            "Nordic",
            "South American",
            "South East Asian"
        )
    }
}