package com.example.foodinfo.model.local.entities.recipe_field

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.foodinfo.model.local.entities.Recipe


@Entity(
    tableName = DietType.TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = Recipe::class,
        parentColumns = arrayOf(Recipe.Columns.ID),
        childColumns = arrayOf(DietType.Columns.RECIPE_ID),
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
    )]
)
data class DietType(
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
        const val RECIPE_ID = "diet_recipe_id"
        const val LABEL = "label"
    }

    enum class Labels(val label: String, val icon: String) {
        LOW_FAT("low-fat", "ic_cat_diet_low_fat"),
        BALANCED("balanced", "ic_cat_diet_balanced"),
        LOW_CARB("low-carb", "ic_cat_diet_low_carb"),
        HIGH_FIBER("high-fiber", "ic_cat_diet_high_fiber"),
        LOW_SODIUM("low-sodium", "ic_cat_diet_low_sodium"),
        HIGH_PROTEIN("high-protein", "ic_cat_diet_high_protein"),
    }

    companion object {
        const val TABLE_NAME = "diet_type"
        val LABELS = Labels.values().map { it.label } as ArrayList
        val ICONS = Labels.values().map { it.icon } as ArrayList
    }
}