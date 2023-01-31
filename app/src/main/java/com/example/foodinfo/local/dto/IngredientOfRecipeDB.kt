package com.example.foodinfo.local.dto


open class IngredientOfRecipeDB(
    open val ID: Int,
    open val recipeID: String,
    open val text: String,
    open val quantity: Float,
    open val measure: String,
    open val weight: Float,
    open val food: String,
    open val foodCategory: String,
    open val foodID: String,
    open val previewURL: String
)