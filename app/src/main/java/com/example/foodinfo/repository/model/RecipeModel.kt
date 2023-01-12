package com.example.foodinfo.repository.model


data class RecipeModel(
    val id: String,
    val name: String,
    val calories: String,
    val totalIngredients: Int,
    val caloriesDaily: Int,
    val source: String,
    val totalWeight: Int,
    val totalTime: Int,
    val servings: Int,
    val previewURL: String,
    val isFavorite: Boolean,
) {

    companion object {
        const val CALORIES_CAP = 2500
    }
}