package com.example.foodinfo.local.dto


open class NutrientOfSearchFilterDB(
    open val ID: Int = 0,
    open val filterName: String,
    open val infoID: Int,
    open val minValue: Float,
    open val maxValue: Float
)