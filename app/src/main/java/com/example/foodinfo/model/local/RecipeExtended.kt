package com.example.foodinfo.model.local

import android.graphics.drawable.Drawable
import com.example.foodinfo.model.local.entities.RecipeExtendedEntity
import com.example.foodinfo.model.local.entities.recipe_field.RecipeIngredientEntity
import com.example.foodinfo.model.local.entities.recipe_field.RecipeNutrientEntity
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
    val ingredients: List<RecipeIngredientEntity>,
    val nutrients: List<RecipeNutrientEntity>,
    val preview: Drawable?
) {

    companion object {
        private const val CALORIES_CAP = 2500
        private const val PROTEIN_CAP = 100
        private const val CARB_CAP = 100
        private const val FAT_CAP = 100

        fun fromEntity(
            entity: RecipeExtendedEntity,
            resourcesProvider: ResourcesProvider
        ): RecipeExtended {
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
                mealType = entity.mealLabels.map { it.label },
                dishType = entity.dishLabels.map { it.label },
                dietType = entity.dietLabels.map { it.label },
                healthType = entity.healthLabels.map { it.label },
                cuisineType = entity.cuisineType.map { it.label },
                ingredients = entity.ingredients,
                nutrients = entity.totalNutrients,
                preview = resourcesProvider.getDrawableByName(entity.previewURL),
            )
        }
    }
}