package com.example.foodinfo.model.local.entities.recipe_field

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.foodinfo.model.local.entities.Recipe


@Entity(
    tableName = DishType.TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = Recipe::class,
        parentColumns = arrayOf(Recipe.Columns.ID),
        childColumns = arrayOf(DishType.Columns.RECIPE_ID),
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
    )]
)
data class DishType(
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
        const val RECIPE_ID = "dish_recipe_id"
        const val LABEL = "label"
    }

    companion object {
        const val TABLE_NAME = "dish_type"
        val validLabels = arrayListOf(
            "alcohol-cocktail",
            "biscuits and cookies",
            "bread",
            "cereals",
            "condiments and sauces",
            "drinks",
            "desserts",
            "egg",
            "main course",
            "omelet",
            "pancake",
            "preps",
            "preserve",
            "salad",
            "sandwiches",
            "soup",
            "starter"
        )
    }
}