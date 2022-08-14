package com.example.foodinfo.repository.mapper

import com.example.foodinfo.local.entity.RecipeIngredientEntity
import com.example.foodinfo.repository.model.RecipeIngredientModel


fun RecipeIngredientEntity.toModel(): RecipeIngredientModel {
    return RecipeIngredientModel(
        id = this.id,
        text = this.text,
        measure = this.measure,
        quantity = this.quantity,
        weight = this.weight,
        food = this.food,
        foodId = this.foodId,
        foodCategory = this.foodCategory,
        previewURL = this.previewURL
    )
}