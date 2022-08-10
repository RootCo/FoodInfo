package com.example.foodinfo.repository.mapper

import com.example.foodinfo.local.entity.RecipeNutrientEntity
import com.example.foodinfo.repository.model.RecipeNutrientModel


fun RecipeNutrientEntity.toModel(): RecipeNutrientModel {
    return RecipeNutrientModel(
        id = this.id,
        label = this.label,
        totalValue = "${this.totalValue} ${this.unit}",
        dailyValue = this.dailyValue.toInt()
    )
}