package com.example.foodinfo.repository.mapper

import com.example.foodinfo.local.entity.LabelEntity
import com.example.foodinfo.repository.model.LabelFilterEditModel
import com.example.foodinfo.repository.model.LabelModel
import com.example.foodinfo.repository.model.SVGModel


fun LabelEntity.toModel(): LabelModel {
    return LabelModel(
        id = this.id,
        category = this.category,
        label = this.label,
        description = this.description,
        preview = SVGModel(this.previewURL)
    )
}

fun LabelEntity.toModelFilterEdit(): LabelFilterEditModel {
    return LabelFilterEditModel(
        id = this.id,
        label = this.label
    )
}