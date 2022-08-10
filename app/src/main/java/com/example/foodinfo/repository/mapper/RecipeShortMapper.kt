package com.example.foodinfo.repository.mapper

import com.example.foodinfo.local.entity.recipe.RecipeShortEntity
import com.example.foodinfo.repository.model.RecipeShortModel


fun RecipeShortEntity.toModel(): RecipeShortModel {
    return RecipeShortModel(
        id = this.id,
        name = this.name,
        calories = this.calories.toString(),
        servings = this.servings.toString(),
        totalTime = "${this.totalTime} min",
        totalIngredients = this.totalIngredients.toString(),
        previewURL = this.previewURL
    )
}