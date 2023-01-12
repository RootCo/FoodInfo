package com.example.foodinfo.repository.model


data class RecipeExtendedModel(
    val fieldInfo: RecipeModel,
    val ingredients: List<String>,
    val categories: List<CategoryRecipeModel>,
    val protein: NutrientRecipeModel,
    val carb: NutrientRecipeModel,
    val fat: NutrientRecipeModel
)