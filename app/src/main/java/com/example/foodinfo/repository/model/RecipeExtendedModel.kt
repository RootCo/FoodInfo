package com.example.foodinfo.repository.model


data class RecipeExtendedModel(
    val id: String,
    val name: String,
    val source: String,
    val totalWeight: Int,
    val totalTime: Int,
    val servings: Int,
    val previewURL: String,
    val isFavorite: Boolean,
    val ingredients: List<String>,
    val categories: List<CategoryRecipeModel>,
    val energy: NutrientRecipeModel,
    val protein: NutrientRecipeModel,
    val carb: NutrientRecipeModel,
    val fat: NutrientRecipeModel
)