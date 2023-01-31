package com.example.foodinfo.local.dto


open class BasicRecipeAttrDB(
    open val ID: Int,
    open val tag: String?,
    open val name: String,
    open val columnName: String,
    open val measure: String,
    open val rangeMin: Float,
    open val rangeMax: Float,
    open val stepSize: Float
)