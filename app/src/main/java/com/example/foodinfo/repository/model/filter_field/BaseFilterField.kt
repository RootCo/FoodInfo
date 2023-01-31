package com.example.foodinfo.repository.model.filter_field


data class BaseFilterField(
    val columnName: String,
    val minValue: Float? = null,
    val maxValue: Float? = null
)