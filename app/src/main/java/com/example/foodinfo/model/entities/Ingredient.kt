package com.example.foodinfo.model.entities

data class Ingredient(
    val name: String,
    val quantity: Int,
    val measure: String,
    val food: String,
    val weight: String,
    val foodCategory: String,
    val foodId: String,
    val previewURL: String
)