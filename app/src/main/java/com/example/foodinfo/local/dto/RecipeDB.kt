package com.example.foodinfo.local.dto


open class RecipeDB(
    open val ID: String,
    open val source: String,
    open val name: String,
    open val previewURL: String,
    open val calories: Int,
    open val ingredientsCount: Int,
    open val weight: Int,
    open val cookingTime: Int,
    open val servings: Int,
    open val isFavorite: Boolean
)