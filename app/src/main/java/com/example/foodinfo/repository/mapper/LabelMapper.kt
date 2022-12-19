package com.example.foodinfo.repository.mapper

import com.example.foodinfo.local.entity.LabelFieldEntity
import com.example.foodinfo.local.entity.LabelFilterEntity
import com.example.foodinfo.local.entity.LabelRecipeEntity
import com.example.foodinfo.repository.model.*


fun LabelFieldEntity.toModelHint(): LabelHintModel {
    return LabelHintModel(
        id = this.id,
        name = this.name,
        description = this.description,
        preview = SVGModel(this.previewURL)
    )
}

fun LabelFieldEntity.toModelSearch(): LabelSearchModel {
    return LabelSearchModel(
        id = this.id,
        name = this.name,
        preview = SVGModel(this.previewURL)
    )
}

fun LabelRecipeEntity.toModelShort(): LabelShortModel {
    return LabelShortModel(
        id = this.id,
        name = this.name
    )
}

fun LabelFilterEntity.toModelEdit(): LabelFilterEditModel {
    return LabelFilterEditModel(
        id = this.id,
        name = this.name,
        isSelected = this.isSelected
    )
}

fun LabelFilterEntity.toModelShort(): LabelShortModel {
    return LabelShortModel(
        id = this.id,
        name = this.name
    )
}


fun LabelFilterEditModel.toEntity(filterName: String, category: String): LabelFilterEntity {
    return LabelFilterEntity(
        id = this.id,
        filterName = filterName,
        category = category,
        name = this.name,
        isSelected = this.isSelected
    )
}