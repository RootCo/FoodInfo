package com.example.foodinfo.local.model.filter_field


/**
 * @param value String value that represents name of desired category
 * @param labels list of all labels that must be present in the recipe
 */
data class CategoryField(
    val value: String,
    val labels: List<String>
)