package com.example.foodinfo.repository.mapper

import com.example.foodinfo.local.entity.RecipeEntity
import com.example.foodinfo.repository.model.RecipeModel
import com.example.foodinfo.repository.model.RecipeShortModel


fun RecipeEntity.toModel(): RecipeModel {
    return RecipeModel(
        id = this.id,
        name = this.name,
        calories = this.calories.toString(),
        caloriesDaily = this.calories * 100 / RecipeModel.CALORIES_CAP,
        source = this.source,
        totalWeight = this.totalWeight,
        totalTime = this.totalTime,
        servings = this.servings.toString(),
        previewURL = this.previewURL
    )
}

fun RecipeEntity.toModelShort(): RecipeShortModel {
    return RecipeShortModel(
        id = this.id,
        name = this.name,
        calories = this.calories.toString(),
        servings = this.servings.toString(),
        totalTime = this.totalTime,
        totalIngredients = this.totalIngredients.toString(),
        previewURL = this.previewURL
    )
}