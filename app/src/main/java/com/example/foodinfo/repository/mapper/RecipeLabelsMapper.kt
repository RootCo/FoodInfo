package com.example.foodinfo.repository.mapper

import com.example.foodinfo.local.entity.RecipeLabelEntity
import com.example.foodinfo.repository.model.RecipeLabelsModel


fun List<RecipeLabelEntity>.toModel(): RecipeLabelsModel {
    return RecipeLabelsModel(
        content = this.groupBy { labelEntity ->
            labelEntity.category
        }.entries.associate { category ->
            category.key to category.value.map { it.label }
        }
    )
}