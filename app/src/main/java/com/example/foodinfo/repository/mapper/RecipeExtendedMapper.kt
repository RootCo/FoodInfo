package com.example.foodinfo.repository.mapper

import com.example.foodinfo.local.entity.recipe.RecipeExtendedEntity
import com.example.foodinfo.local.model.RecipeExtendedModel


fun RecipeExtendedEntity.toModel(): RecipeExtendedModel {
    return RecipeExtendedModel(
        id = this.id,
        name = this.name,
        calories = this.calories.toString(),
        caloriesDaily = this.calories * 100 / RecipeExtendedModel.CALORIES_CAP,
        protein = "${this.protein}g",
        proteinDaily = this.protein * 100 / RecipeExtendedModel.PROTEIN_CAP,
        carb = "${this.carb}g",
        carbDaily = this.carb * 100 / RecipeExtendedModel.CARB_CAP,
        fat = "${this.fat}g",
        fatDaily = this.fat * 100 / RecipeExtendedModel.FAT_CAP,
        source = this.source,
        totalWeight = "${this.totalWeight}g",
        totalTime = "${this.totalTime} min",
        servings = this.servings.toString(),
        categories = this.labels.groupBy { labelEntity ->
            labelEntity.category
        }.entries.associate { category ->
            category.key to category.value.map { it.label }
        },
        ingredients = this.ingredients.map { it.toModel() },
        nutrients = this.nutrients.map { it.toModel() },
        previewURL = this.previewURL
    )
}