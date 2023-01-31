package com.example.foodinfo.local.dto


open class NutrientOfRecipeDB(
    open val ID: Int = 0,
    open val recipeID: String,
    open val infoID: Int,
    open val value: Float
)