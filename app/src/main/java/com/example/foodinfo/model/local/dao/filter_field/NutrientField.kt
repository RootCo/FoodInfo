package com.example.foodinfo.model.local.dao.filter_field


/**
 * if minValue == null query will be like: 'field <= maxValue'
 *
 * if maxValue == null query will be like: 'field >= minValue'
 *
 * if both != null query will be like: 'field BETWEEN minValue AND maxValue'
 *
 * @param value String value that represents name of desired nutrient
 * @param minValue positive float, specifies the minimum value of the field
 * @param maxValue positive float, specifies the maximum value of the field
 */
data class NutrientField(
    val value: String,
    val minValue: Float? = null,
    val maxValue: Float? = null
)