package com.example.foodinfo.repository.model


data class RecipeExtendedModel(
    val id: String,
    val name: String,
    val calories: String,
    val caloriesDaily: Int,
    val protein: String,
    val proteinDaily: Int,
    val fat: String,
    val fatDaily: Int,
    val carb: String,
    val carbDaily: Int,
    val source: String,
    val totalWeight: String,
    val totalTime: String,
    val servings: String,
    val categories: Map<String, List<String>>,
    val ingredients: List<RecipeIngredientModel>,
    val nutrients: List<RecipeNutrientModel>,
    val previewURL: String
) {

    companion object {
        const val CALORIES_CAP = 2500
        const val PROTEIN_CAP = 100
        const val CARB_CAP = 100
        const val FAT_CAP = 100
    }
}