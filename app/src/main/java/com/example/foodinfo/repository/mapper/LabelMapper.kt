package com.example.foodinfo.repository.mapper

import com.example.foodinfo.local.entity.LabelEntity
import com.example.foodinfo.local.model.LabelModel


fun LabelEntity.toModel(): LabelModel {
    return LabelModel(
        id = this.id,
        category = this.category,
        label = this.label,
        description = this.description,
        previewURL = this.previewURL
    )
}