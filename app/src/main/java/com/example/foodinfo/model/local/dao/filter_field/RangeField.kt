package com.example.foodinfo.model.local.dao.filter_field

import com.example.foodinfo.model.local.entities.Recipe


data class RangeField(
    val value: Fields,
    val minValue: Int? = null,
    val maxValue: Int? = null
) {
    enum class Fields(val column: String, val label: String) {
        FAT(Recipe.Columns.FAT, "fat"),
        CARB(Recipe.Columns.CARB, "carb"),
        SOURCE(Recipe.Columns.SOURCE, "source"),
        PROTEIN(Recipe.Columns.PROTEIN, "protein"),
        SERVINGS(Recipe.Columns.SERVINGS, "servings"),
        CALORIES(Recipe.Columns.CALORIES, "calories"),
        TOTAL_TIME(Recipe.Columns.TOTAL_TIME, "total time"),
        TOTAL_WEIGHT(Recipe.Columns.TOTAL_WEIGHT, "total weight"),
    }

    companion object {
        fun fromLabel(label: String) =
            NutrientField.Fields.values().first { it.label == label }
    }
}

class RangeFields(private val _data: MutableSet<RangeField> = mutableSetOf()) {
    val data: MutableSet<RangeField>
        get() = _data

    fun add(field: RangeField) {
        _data.add(field)
    }
}