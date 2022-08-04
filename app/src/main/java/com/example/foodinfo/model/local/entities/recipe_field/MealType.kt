package com.example.foodinfo.model.local.entities.recipe_field

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.foodinfo.model.local.entities.Recipe


@Entity(
    tableName = MealType.TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = Recipe::class,
        parentColumns = arrayOf(Recipe.Columns.ID),
        childColumns = arrayOf(MealType.Columns.RECIPE_ID),
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
    )]
)
data class MealType(
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

    enum class Labels(val label: String, val icon: String) {
        LUNCH("lunch", "ic_cat_meal_lunch"),
        SNACK("snack", "ic_cat_meal_snack"),
        DINNER("dinner", "ic_cat_meal_dinner"),
        TEATIME("teatime", "ic_cat_meal_teatime"),
        BREAKFAST("breakfast", "ic_cat_meal_breakfast"),
    }

    companion object {
        const val TABLE_NAME = "meal_type"
        val LABELS = Labels.values().map { it.label } as ArrayList
        val ICONS = Labels.values().map { it.icon } as ArrayList
    }
}