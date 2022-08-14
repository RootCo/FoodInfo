package com.example.foodinfo.utils


fun getMeasureSpacer(measure: String): String {
    return when (measure) {
        "g",
        "mg",
        "ug" -> ""
        else -> " "
    }
}