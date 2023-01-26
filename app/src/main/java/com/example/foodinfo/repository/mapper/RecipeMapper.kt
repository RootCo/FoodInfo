package com.example.foodinfo.repository.mapper

import com.example.foodinfo.local.pojo.RecipeExtendedPOJO
import com.example.foodinfo.local.pojo.RecipePOJO
import com.example.foodinfo.repository.model.RecipeExtendedModel
import com.example.foodinfo.repository.model.RecipeFavoriteModel
import com.example.foodinfo.repository.model.RecipeShortModel


fun RecipePOJO.toModelShort(): RecipeShortModel {
    return RecipeShortModel(
        id = this.id,
        name = this.name,
        calories = this.calories.toString(),
        servings = this.servings.toString(),
        cookingTime = this.cookingTime,
        ingredientsCount = this.ingredientsCount.toString(),
        previewURL = this.previewURL,
        isFavorite = this.isFavorite
    )
}

fun RecipePOJO.toModelFavorite(): RecipeFavoriteModel {
    return RecipeFavoriteModel(
        id = this.id,
        name = this.name,
        source = this.source,
        calories = this.calories.toString(),
        servings = this.servings.toString(),
        previewURL = this.previewURL
    )
}

fun RecipeExtendedPOJO.toModelExtended(): RecipeExtendedModel {
    return RecipeExtendedModel(
        id = this.id,
        name = this.name,
        source = this.source,
        totalWeight = this.weight,
        totalTime = this.cookingTime,
        servings = this.servings,
        previewURL = this.previewURL,
        isFavorite = this.isFavorite,
        ingredients = this.ingredients.map { it.previewURL },
        categories = this.labels.toModelRecipe(),
        energy = this.nutrients.findLast { it.name == "Protein" }!!.toModel(), // TODO change to "Energy"
        protein = this.nutrients.findLast { it.name == "Protein" }!!.toModel(),
        carb = this.nutrients.findLast { it.name == "Carbs" }!!.toModel(),
        fat = this.nutrients.findLast { it.name == "Fat" }!!.toModel()
    )
}