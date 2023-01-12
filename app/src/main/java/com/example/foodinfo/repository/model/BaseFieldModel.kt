package com.example.foodinfo.repository.model

data class BaseFieldModel(
    val id: Long = 0,
    val name: String,
    val measure: String,
    val rangeMin: Float,
    val rangeMax: Float,
    val stepSize: Float
)
