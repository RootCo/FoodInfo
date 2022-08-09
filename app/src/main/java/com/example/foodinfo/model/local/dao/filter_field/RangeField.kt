package com.example.foodinfo.model.local.dao.filter_field

import com.example.foodinfo.model.local.dao.filter_field.RangeField.Fields
import com.example.foodinfo.model.local.entities.recipe.RecipeEntity


/**
 * if minValue == null query will be like: 'field <= maxValue'
 *
 * if maxValue == null query will be like: 'field >= minValue'
 *
 * if both != null query will be like: 'field BETWEEN minValue AND maxValue'
 *
 * @param value [Fields] object that contains all necessary information for
 * query generation
 * @param minValue positive float, specifies the minimum value of the field
 * @param maxValue positive float, specifies the maximum value of the field
 */
data class RangeField(
    val value: Fields,
    val minValue: Int? = null,
    val maxValue: Int? = null
) {
    /**
     * @param column database table column name that will be used in query
     * @param label database table column name that will be presented to user
     */
    enum class Fields(val column: String, val label: String) {
        FAT(RecipeEntity.Columns.FAT, "fat"),
        CARB(RecipeEntity.Columns.CARB, "carb"),
        SOURCE(RecipeEntity.Columns.SOURCE, "source"),
        PROTEIN(RecipeEntity.Columns.PROTEIN, "protein"),
        SERVINGS(RecipeEntity.Columns.SERVINGS, "servings"),
        CALORIES(RecipeEntity.Columns.CALORIES, "calories"),
        TOTAL_TIME(RecipeEntity.Columns.TOTAL_TIME, "time"),
        TOTAL_WEIGHT(RecipeEntity.Columns.TOTAL_WEIGHT, "weight"),
        TOTAL_INGREDIENTS(RecipeEntity.Columns.TOTAL_INGREDIENTS, "ingredients"),
    }

    companion object {
        fun fromLabel(label: String) = Fields.values().first { it.label == label }
    }
}