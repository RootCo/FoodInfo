package com.example.foodinfo.model.local.dao.filter_field

import com.example.foodinfo.model.local.dao.filter_field.RangeField.Fields
import com.example.foodinfo.model.local.entities.Recipe


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
) : BaseField {
    /**
     * @param column database table column name that will be used in query
     * @param label database table column name that will be presented to user
     */
    enum class Fields(val column: String, val label: String) {
        FAT(Recipe.Columns.FAT, "fat"),
        CARB(Recipe.Columns.CARB, "carb"),
        SOURCE(Recipe.Columns.SOURCE, "source"),
        PROTEIN(Recipe.Columns.PROTEIN, "protein"),
        SERVINGS(Recipe.Columns.SERVINGS, "servings"),
        CALORIES(Recipe.Columns.CALORIES, "calories"),
        TOTAL_TIME(Recipe.Columns.TOTAL_TIME, "time"),
        TOTAL_WEIGHT(Recipe.Columns.TOTAL_WEIGHT, "weight"),
        TOTAL_INGREDIENTS(Recipe.Columns.TOTAL_INGREDIENTS, "ingredients"),
    }

    companion object {
        fun fromLabel(label: String) =
            NutrientField.Fields.values().first { it.label == label }
    }
}

class RangeFields(_data: MutableSet<RangeField> = mutableSetOf()) :
    BaseFieldSet<RangeField>(_data)
