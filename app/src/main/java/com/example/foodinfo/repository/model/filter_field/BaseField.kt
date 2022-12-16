package com.example.foodinfo.repository.model.filter_field

import com.example.foodinfo.local.entity.RecipeEntity


data class BaseField(
    val name: String,
    val minValue: Double? = null,
    val maxValue: Double? = null
)