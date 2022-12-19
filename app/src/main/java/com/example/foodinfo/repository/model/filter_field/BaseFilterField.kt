package com.example.foodinfo.repository.model.filter_field


data class BaseFilterField(
    val name: String,
    val minValue: Float? = null,
    val maxValue: Float? = null
)