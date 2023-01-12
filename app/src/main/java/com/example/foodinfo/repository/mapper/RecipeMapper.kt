package com.example.foodinfo.repository.mapper

import com.example.foodinfo.local.entity.RecipeEntity
import com.example.foodinfo.local.pojo.RecipeExtendedPOJO
import com.example.foodinfo.repository.model.RecipeExtendedModel
import com.example.foodinfo.repository.model.RecipeFavoriteModel
import com.example.foodinfo.repository.model.RecipeModel
import com.example.foodinfo.repository.model.RecipeShortModel


fun RecipeEntity.toModelShort(): RecipeShortModel {
    return RecipeShortModel(
        id = this.id,
        name = this.name,
        calories = this.calories.toString(),
        servings = this.servings.toString(),
        totalTime = this.totalTime,
        totalIngredients = this.totalIngredients.toString(),
        previewURL = this.previewURL,
        isFavorite = this.isFavorite
    )
}

fun RecipeEntity.toModelFavorite(): RecipeFavoriteModel {
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
        fieldInfo = this.recipe.toModel(),
        ingredients = this.ingredients.map { it.previewURL },
        categories = this.labels.toModelRecipe(),
        protein = this.nutrients.findLast { it.nutrient.name == "Protein" }!!.toModel(), //TODO это что за странный маппинг?
        carb = this.nutrients.findLast { it.nutrient.name == "Carbs" }!!.toModel(),
        fat = this.nutrients.findLast { it.nutrient.name == "Fat" }!!.toModel()
    )
}

fun RecipeEntity.toModel(): RecipeModel {
    return RecipeModel(
        id = this.id,
        name = this.name,
        calories = this.calories.toString(),
        totalIngredients = this.totalIngredients,
        caloriesDaily = this.calories * 100 / RecipeModel.CALORIES_CAP,
        source = this.source,
        totalWeight = this.totalWeight,
        totalTime = this.totalTime,
        servings = this.servings,
        previewURL = this.previewURL,
        isFavorite = this.isFavorite,
    )
}

fun RecipeModel.toEntity(): RecipeEntity {
    return RecipeEntity(
        id = this.id,
        name = this.name,
        calories = this.calories.toInt(),
        totalIngredients = this.totalIngredients,
        source = this.source,
        totalWeight = this.totalWeight,
        totalTime = this.totalTime,
        servings = this.servings,
        previewURL = this.previewURL,
        isFavorite = this.isFavorite,
    )
}
