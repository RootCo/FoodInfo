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

    enum class Labels(val label: String, val icon: String) {
        ASIAN("Asian", "ic_cat_cuisine_asian"),
        FRENCH("French", "ic_cat_cuisine_french"),
        INDIAN("Indian", "ic_cat_cuisine_indian"),
        KOSHER("Kosher", "ic_cat_cuisine_kosher"),
        NORDIC("Nordic", "ic_cat_cuisine_nordic"),
        ITALIAN("Italian", "ic_cat_cuisine_italian"),
        BRITISH("British", "ic_cat_cuisine_british"),
        CHINESE("Chinese", "ic_cat_cuisine_chinese"),
        MEXICAN("Mexican", "ic_cat_cuisine_mexican"),
        JAPANESE("Japanese", "ic_cat_cuisine_japanese"),
        AMERICAN("American", "ic_cat_cuisine_american"),
        CARIBBEAN("Caribbean", "ic_cat_cuisine_caribbean"),
        MEDITERRANEAN("Mediterranean", "ic_cat_cuisine_mediterranean"),
        CENTRAL_EUROPE("Central Europe", "ic_cat_cuisine_central_europe"),
        EASTERN_EUROPE("Eastern Europe", "ic_cat_cuisine_eastern_europe"),
        MIDDLE_EASTERN("Middle Eastern", "ic_cat_cuisine_middle_eastern"),
        SOUTH_AMERICAN("South American", "ic_cat_cuisine_south_american"),
        SOUTH_EAST_ASIAN("South East Asian", "ic_cat_cuisine_south_east_asian")
    }

    companion object {
        const val TABLE_NAME = "cuisine_type"
        val LABELS = Labels.values().map { it.label } as ArrayList
        val ICONS = Labels.values().map { it.icon } as ArrayList
    }
}