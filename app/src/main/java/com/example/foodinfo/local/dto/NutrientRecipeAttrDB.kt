package com.example.foodinfo.local.dto


open class NutrientRecipeAttrDB(
    open val ID: Int,
    open val tag: String,
    open val name: String,
    open val measure: String,
    open val description: String,
    open val hasRDI: Boolean,
    open val previewURL: String,
    open val dailyAllowance: Float,
    open val stepSize: Float,
    open val rangeMin: Float,
    open val rangeMax: Float
)