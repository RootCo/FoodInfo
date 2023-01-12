package com.example.foodinfo.repository.mapper

import com.example.foodinfo.local.entity.NutrientFieldEntity
import com.example.foodinfo.local.entity.NutrientFilterEntity
import com.example.foodinfo.local.entity.NutrientRecipeEntity
import com.example.foodinfo.repository.model.*
import com.example.foodinfo.repository.model.filter_field.NutrientFilterField


fun NutrientFieldEntity.toModelHint(): NutrientHintModel {
    return NutrientHintModel(
        id = this.id,
        label = this.name,
        description = this.description,
        preview = SVGModel(this.previewURL)
    )
}

fun NutrientRecipeEntity.toModel(): NutrientRecipeModel {
    return NutrientRecipeModel(
        id = this.id,
        fieldInfo = this.nutrient.toModel(),
        totalWeight = this.totalValue,
        dailyPercent = (this.totalValue * 100 / this.nutrient.dailyAllowance).toInt()
    )
}

fun NutrientFilterEntity.toModelEdit(): NutrientFilterEditModel {
    return NutrientFilterEditModel(
        id = this.id,
        fieldInfo = this.fieldInfo.toModel(),
        minValue = this.minValue,
        maxValue = this.maxValue
    )
}

fun List<NutrientFilterEntity>.toModelPreview(): List<NutrientFilterPreviewModel> {
    return this.mapNotNull { nutrient ->
        if (
            nutrient.minValue == nutrient.fieldInfo.rangeMin &&
            nutrient.maxValue == nutrient.fieldInfo.rangeMax
        ) {
            null
        } else {
            NutrientFilterPreviewModel(
                id = nutrient.id,
                name = nutrient.fieldInfo.name,
                measure = nutrient.fieldInfo.measure,
                minValue = nutrient.minValue,
                maxValue = nutrient.maxValue,
            )
        }
    }
}

fun List<NutrientFilterEntity>.toModelFilterField(): List<NutrientFilterField> {
    return this.map { nutrient ->
        NutrientFilterField(
            name = nutrient.fieldInfo.name,
            minValue = if (nutrient.minValue == nutrient.fieldInfo.rangeMin) null else nutrient.minValue,
            maxValue = if (nutrient.maxValue == nutrient.fieldInfo.rangeMax) null else nutrient.maxValue
        )
    }.filter { !(it.minValue == null && it.maxValue == null) }
}


fun NutrientFilterEditModel.toEntity(filterName: String): NutrientFilterEntity {
    return NutrientFilterEntity(
        id = this.id,
        fieldInfo = this.fieldInfo.toEntity(),
        filterName = filterName,
        minValue = this.minValue,
        maxValue = this.maxValue
    )
}

fun NutrientFieldEntity.toModel(): NutrientFieldModel {
    return NutrientFieldModel(
        id = this.id,
        name = this.name,
        tag = this.tag,
        measure = this.measure,
        rangeMin = this.rangeMin,
        rangeMax = this.rangeMax,
        stepSize = this.stepSize,
        dailyAllowance = this.dailyAllowance,
        description = this.description,
        previewURL = this.previewURL,
    )
}

fun NutrientFieldModel.toEntity(): NutrientFieldEntity {
    return NutrientFieldEntity(
        id = this.id,
        name = this.name,
        tag = this.tag,
        measure = this.measure,
        rangeMin = this.rangeMin,
        rangeMax = this.rangeMax,
        stepSize = this.stepSize,
        dailyAllowance = this.dailyAllowance,
        description = this.description,
        previewURL = this.previewURL,
    )
}