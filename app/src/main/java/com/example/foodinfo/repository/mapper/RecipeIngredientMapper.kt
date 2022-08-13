package com.example.foodinfo.repository.mapper

import com.example.foodinfo.local.entity.RecipeIngredientEntity
import com.example.foodinfo.repository.model.RecipeIngredientModel
import java.text.DecimalFormat


// TODO proper conversion for quality and weight fields
fun RecipeIngredientEntity.toModel(): RecipeIngredientModel {
    return RecipeIngredientModel(
        id = this.id,
        text = this.text,
        quantity = "${DecimalFormat("0.##").format(this.quantity)} ${this.measure}",
        weight = "${DecimalFormat("0.##").format(this.weight)}g",
        food = this.food,
        foodId = this.foodId,
        foodCategory = this.foodCategory,
        previewURL = this.previewURL
    )
}