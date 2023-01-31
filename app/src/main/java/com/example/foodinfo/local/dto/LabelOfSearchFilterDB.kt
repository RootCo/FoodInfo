package com.example.foodinfo.local.dto


open class LabelOfSearchFilterDB(
    open val ID: Int = 0,
    open val filterName: String,
    open val infoID: Int,
    open val isSelected: Boolean
)