package com.example.foodinfo.model.entities

import android.graphics.drawable.Drawable

data class RecipeShort(
    val id: String,
    val name: String,
    val calories: Int,
    val previewURL: String,
    var preview: Drawable?
)