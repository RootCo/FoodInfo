package com.example.foodinfo.repository.mapper

import com.example.foodinfo.local.entity.NutrientFieldEntity
import com.example.foodinfo.local.entity.NutrientFilterEntity
import com.example.foodinfo.local.pojo.NutrientFilterPOJO
import com.example.foodinfo.local.pojo.NutrientRecipePOJO
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

fun NutrientRecipePOJO.toModel(): NutrientRecipeModel {
    return NutrientRecipeModel(
        id = this.id,
        label = this.name,
        measure = this.fieldInfo.measure,
        totalWeight = this.totalValue,
        dailyWeight = this.fieldInfo.dailyAllowance,
        dailyPercent = (this.totalValue * 100 / this.fieldInfo.dailyAllowance).toInt()
    )
}

fun NutrientFilterPOJO.toModelEdit(): NutrientFilterEditModel {
    return NutrientFilterEditModel(
        id = this.id,
        name = this.name,
        stepSize = this.fieldInfo.stepSize,
        measure = this.fieldInfo.measure,
        rangeMin = this.fieldInfo.rangeMin,
        rangeMax = this.fieldInfo.rangeMax,
        minValue = this.minValue,
        maxValue = this.maxValue
    )
}

fun List<NutrientFilterPOJO>.toModelPreview(): List<NutrientFilterPreviewModel> {
    return this.mapNotNull { nutrient ->
        if (
            nutrient.minValue == nutrient.fieldInfo.rangeMin &&
            nutrient.maxValue == nutrient.fieldInfo.rangeMax
        ) {
            null
        } else {
            NutrientFilterPreviewModel(
                id = nutrient.id,
                name = nutrient.name,
                measure = nutrient.fieldInfo.measure,
                minValue = nutrient.minValue,
                maxValue = nutrient.maxValue,
            )
        }
    }
}

fun List<NutrientFilterPOJO>.toModelFilterField(): List<NutrientFilterField> {
    return this.map { nutrient ->
        NutrientFilterField(
            name = nutrient.name,
            minValue = if (nutrient.minValue == nutrient.fieldInfo.rangeMin) null else nutrient.minValue,
            maxValue = if (nutrient.maxValue == nutrient.fieldInfo.rangeMax) null else nutrient.maxValue
        )
    }.filter { !(it.minValue == null && it.maxValue == null) }
}


fun NutrientFilterEditModel.toEntity(filterName: String): NutrientFilterEntity {
    return NutrientFilterEntity(
        id = this.id,
        name = this.name,
        filterName = filterName,
        minValue = this.minValue,
        maxValue = this.maxValue
    )
}