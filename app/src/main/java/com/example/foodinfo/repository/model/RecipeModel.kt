package com.example.foodinfo.repository.model


data class RecipeModel(
    val id: String,
    val name: String,
    val calories: String,
    val caloriesDaily: Int,
    val source: String,
    val totalWeight: String,
    val totalTime: String,
    val servings: String,
    val previewURL: String
) {

    companion object {
        const val CALORIES_CAP = 2500
    }
}