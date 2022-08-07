package com.example.foodinfo.model.local

import android.graphics.drawable.Drawable
import com.example.foodinfo.model.local.entities.recipe_field.Ingredient
import com.example.foodinfo.model.local.entities.recipe_field.Nutrient
import com.example.foodinfo.utils.ResourcesProvider


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
    val mealType: List<String>,
    val dishType: List<String>,
    val dietType: List<String>,
    val healthType: List<String>,
    val cuisineType: List<String>,
    val ingredients: List<Ingredient>,
    val nutrients: List<Nutrient>,
    val preview: Drawable?
) {

    companion object {
        private const val CALORIES_CAP = 2500
        private const val PROTEIN_CAP = 100
        private const val CARB_CAP = 100
        private const val FAT_CAP = 100

        fun fromDTO(
            recipe: RecipeExtendedDTO,
            resourcesProvider: ResourcesProvider
        ): RecipeExtended {
            return RecipeExtended(
                id = recipe.id,
                name = recipe.name,
                calories = recipe.calories.toString(),
                caloriesDaily = recipe.calories * 100 / CALORIES_CAP,
                protein = recipe.protein.toString() + "g",
                proteinDaily = recipe.protein * 100 / PROTEIN_CAP,
                carb = recipe.carb.toString() + "g",
                carbDaily = recipe.carb * 100 / CARB_CAP,
                fat = recipe.fat.toString() + "g",
                fatDaily = recipe.fat * 100 / FAT_CAP,
                source = recipe.source,
                totalWeight = recipe.totalWeight.toString() + "g",
                totalTime = recipe.totalTime.toString() + " min",
                servings = recipe.servings.toString(),
                mealType = recipe.mealType.map { it.label },
                dishType = recipe.dishType.map { it.label },
                dietType = recipe.dietType.map { it.label },
                healthType = recipe.healthType.map { it.label },
                cuisineType = recipe.cuisineType.map { it.label },
                ingredients = recipe.ingredients,
                nutrients = recipe.totalNutrients,
                preview = resourcesProvider.getDrawableByName(recipe.previewURL),
            )
        }
    }
}