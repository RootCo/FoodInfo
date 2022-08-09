package com.example.foodinfo.local.model

import com.example.foodinfo.local.entity.recipe.RecipeExtendedEntity


data class RecipeExtended(
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
    val ingredients: List<RecipeIngredient>,
    val nutrients: List<RecipeNutrient>,
    val previewURL: String
) {

    companion object {
        private const val CALORIES_CAP = 2500
        private const val PROTEIN_CAP = 100
        private const val CARB_CAP = 100
        private const val FAT_CAP = 100

        fun fromEntity(entity: RecipeExtendedEntity): RecipeExtended {
            return RecipeExtended(
                id = entity.id,
                name = entity.name,
                calories = entity.calories.toString(),
                caloriesDaily = entity.calories * 100 / CALORIES_CAP,
                protein = "${entity.protein}g",
                proteinDaily = entity.protein * 100 / PROTEIN_CAP,
                carb = "${entity.carb}g",
                carbDaily = entity.carb * 100 / CARB_CAP,
                fat = "${entity.fat}g",
                fatDaily = entity.fat * 100 / FAT_CAP,
                source = entity.source,
                totalWeight = "${entity.totalWeight}g",
                totalTime = "${entity.totalTime} min",
                servings = entity.servings.toString(),
                categories = entity.labels.groupBy { labelEntity ->
                    labelEntity.category
                }.entries.associate { category ->
                    category.key to category.value.map { it.label }
                },
                ingredients = entity.ingredients.map {
                    RecipeIngredient.fromEntity(it)
                },
                nutrients = entity.nutrients.map {
                    RecipeNutrient.fromEntity(it)
                },
                previewURL = entity.previewURL
            )
        }
    }
}