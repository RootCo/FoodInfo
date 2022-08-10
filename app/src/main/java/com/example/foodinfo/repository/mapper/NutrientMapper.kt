package com.example.foodinfo.repository.mapper

import com.example.foodinfo.local.entity.NutrientEntity
import com.example.foodinfo.local.model.NutrientModel


fun NutrientEntity.toModel(): NutrientModel {
    return NutrientModel(
        id = this.id,
        tag = this.tag,
        label = this.label,
        description = this.description,
        previewURL = this.previewURL
    )
}