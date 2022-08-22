package com.example.foodinfo.repository.mapper

import com.example.foodinfo.local.entity.RecipePOJO
import com.example.foodinfo.repository.model.RecipeModel
import com.example.foodinfo.repository.model.RecipeShortModel


fun RecipePOJO.toModel(): RecipeModel {
    return RecipeModel(
        id = this.id,
        name = this.name,
        calories = this.calories.toString(),
        caloriesDaily = this.calories * 100 / RecipeModel.CALORIES_CAP,
        source = this.source,
        totalWeight = this.totalWeight,
        totalTime = this.totalTime,
        servings = this.servings,
        previewURL = this.previewURL,
        isFavorite = this.favoriteMark.isFavorite
    )
}

fun RecipePOJO.toModelShort(): RecipeShortModel {
    return RecipeShortModel(
        id = this.id,
        name = this.name,
        calories = this.calories.toString(),
        servings = this.servings.toString(),
        totalTime = this.totalTime,
        totalIngredients = this.totalIngredients.toString(),
        previewURL = this.previewURL,
        isFavorite = this.favoriteMark.isFavorite
    )
}