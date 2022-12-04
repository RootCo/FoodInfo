package com.example.foodinfo.repository.mapper

import com.example.foodinfo.local.entity.RecipeLabelEntity
import com.example.foodinfo.repository.model.RecipeCategoryModel


fun List<RecipeLabelEntity>.toModel(): List<RecipeCategoryModel> {
    return this.groupBy { labelEntity ->
        labelEntity.category
    }.entries.map { entry ->
        RecipeCategoryModel(entry.key, entry.value.map { it.label })
    }
}