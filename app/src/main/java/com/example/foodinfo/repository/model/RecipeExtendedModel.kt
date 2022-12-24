package com.example.foodinfo.repository.model


data class RecipeExtendedModel(
    val id: String,
    val name: String,
    val calories: String,
    val caloriesDaily: Int,
    val source: String,
    val totalWeight: Int,
    val totalTime: Int,
    val servings: Int,
    val previewURL: String,
    val isFavorite: Boolean,
    val ingredients: List<RecipeIngredientModel>,
    val categories: List<CategoryRecipeModel>,
    val protein: NutrientRecipeModel,
    val carb: NutrientRecipeModel,
    val fat: NutrientRecipeModel
)