package com.example.foodinfo.repository.mapper

import com.example.foodinfo.local.entity.RecipeLabelEntity
import com.example.foodinfo.repository.model.CategoryLabelsModel


fun List<RecipeLabelEntity>.toModel(): List<CategoryLabelsModel> {
    return this.groupBy { labelEntity ->
        labelEntity.category
    }.entries.map { entry ->
        CategoryLabelsModel(entry.key, entry.value.map { it.label })
    }
}