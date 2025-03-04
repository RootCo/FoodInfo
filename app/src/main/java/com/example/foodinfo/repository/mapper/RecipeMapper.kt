package com.example.foodinfo.repository.mapper

import com.example.foodinfo.local.dto.RecipeDB
import com.example.foodinfo.local.dto.RecipeExtendedDB
import com.example.foodinfo.repository.model.RecipeExtendedModel
import com.example.foodinfo.repository.model.RecipeFavoriteModel
import com.example.foodinfo.repository.model.RecipeShortModel


fun RecipeDB.toModelShort(): RecipeShortModel {
    return RecipeShortModel(
        ID = this.ID,
        name = this.name,
        calories = this.calories.toString(),
        servings = this.servings.toString(),
        cookingTime = this.cookingTime,
        ingredientsCount = this.ingredientsCount.toString(),
        previewURL = this.previewURL,
        isFavorite = this.isFavorite
    )
}

fun RecipeDB.toModelFavorite(): RecipeFavoriteModel {
    return RecipeFavoriteModel(
        ID = this.ID,
        name = this.name,
        source = this.source,
        calories = this.calories.toString(),
        servings = this.servings.toString(),
        previewURL = this.previewURL
    )
}

fun RecipeExtendedDB.toModelExtended(): RecipeExtendedModel {
    return RecipeExtendedModel(
        ID = this.ID,
        name = this.name,
        weight = this.weight,
        cookingTime = this.cookingTime,
        servings = this.servings,
        previewURL = this.previewURL,
        isFavorite = this.isFavorite,
        ingredients = this.ingredients.map { it.previewURL },
        categories = this.labels.toModelRecipe(),
        energy = this.nutrients.findLast { it.infoID == 1 }!!.toModel(),
        protein = this.nutrients.findLast { it.infoID == 12 }!!.toModel(),
        carb = this.nutrients.findLast { it.infoID == 7 }!!.toModel(),
        fat = this.nutrients.findLast { it.infoID == 2 }!!.toModel()
    )
}