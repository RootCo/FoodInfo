package com.example.foodinfo.local.dto


open class BasicOfSearchFilterDB(
    open val ID: Int = 0,
    open val infoID: Int,
    open val filterName: String,
    open val minValue: Float,
    open val maxValue: Float
)