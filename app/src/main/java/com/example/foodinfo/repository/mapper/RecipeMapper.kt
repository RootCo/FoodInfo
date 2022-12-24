package com.example.foodinfo.repository.mapper

import com.example.foodinfo.local.pojo.RecipeExtendedPOJO
import com.example.foodinfo.local.pojo.RecipePOJO
import com.example.foodinfo.repository.model.RecipeExtendedModel
import com.example.foodinfo.repository.model.RecipeFavoriteModel
import com.example.foodinfo.repository.model.RecipeModel
import com.example.foodinfo.repository.model.RecipeShortModel


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
        calories = this.calories.toString(),
        caloriesDaily = this.calories * 100 / RecipeModel.CALORIES_CAP,
        source = this.source,
        totalWeight = this.totalWeight,
        totalTime = this.totalTime,
        servings = this.servings,
        previewURL = this.previewURL,
        isFavorite = this.favoriteMark.isFavorite,
        ingredients = this.ingredients.map { it.toModel() },
        categories = this.labels.toModelRecipe(),
        protein = this.nutrients.findLast { it.name == "Protein" }!!.toModel(),
        carb = this.nutrients.findLast { it.name == "Carbs" }!!.toModel(),
        fat = this.nutrients.findLast { it.name == "Fat" }!!.toModel()
    )
}