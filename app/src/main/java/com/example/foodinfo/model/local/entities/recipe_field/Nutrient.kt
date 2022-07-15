package com.example.foodinfo.model.local.entities.recipe_field

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.foodinfo.model.local.entities.Recipe


/*
    в основном большие объемы рецептов будут запрашиваться в виде RecipeShort,
    а nutrients и ingredients будут нужны только на экране с детальным рассмотрением
    рецепта, который будет запрашиваться штучно по его id
    я думал, что запрос из таблицы с 10 полями будет быстрее чем с 40, но оказалось
    одинаково (ну или криво тестил)
    но все таки я считаю что лучше вынести их в отдельную таблицу, чем иметь Entity
    на с доп 34 полями
 */
@Entity(
    tableName = Nutrient.TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = Recipe::class,
        parentColumns = arrayOf(Recipe.Columns.ID),
        childColumns = arrayOf(Nutrient.Columns.RECIPE_ID),
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
    )]
)
data class Nutrient(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Columns.ID)
    val id: Long,

    @ColumnInfo(name = Columns.RECIPE_ID)
    val recipeId: String,

    @ColumnInfo(name = Columns.LABEL)
    val label: String,

    @ColumnInfo(name = Columns.QUANTITY)
    val quantity: Float,

    @ColumnInfo(name = Columns.UNIT)
    val unit: String
) {
    object Columns {
        const val ID = "id"
        const val RECIPE_ID = "nutrient_recipe_id"
        const val LABEL = "label"
        const val QUANTITY = "quantity"
        const val UNIT = "unit"
    }

    companion object {
        const val TABLE_NAME = "nutrient"
        const val SELECTOR = "SELECT * FROM $TABLE_NAME"
    }
}