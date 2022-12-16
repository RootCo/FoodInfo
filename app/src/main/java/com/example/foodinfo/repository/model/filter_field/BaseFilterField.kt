package com.example.foodinfo.repository.model.filter_field


data class BaseFilterField(
    val name: String,
    val minValue: Double? = null,
    val maxValue: Double? = null
)