package com.example.foodinfo.repository.model

data class NutrientFieldModel(

    val id: Long = 0,
    val name: String,
    val tag: String,
    val measure: String,
    val rangeMin: Float,
    val rangeMax: Float,
    val stepSize: Float,
    val dailyAllowance: Float,
    val description: String,
    val previewURL: String,
)