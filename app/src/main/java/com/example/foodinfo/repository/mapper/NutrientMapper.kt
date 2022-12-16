package com.example.foodinfo.repository.mapper

import com.example.foodinfo.local.entity.NutrientEntity
import com.example.foodinfo.local.entity.NutrientFilterEntity
import com.example.foodinfo.local.entity.NutrientFilterPOJO
import com.example.foodinfo.local.entity.NutrientRecipePOJO
import com.example.foodinfo.repository.model.*
import java.lang.Integer.min


fun NutrientEntity.toModel(): NutrientModel {
    return NutrientModel(
        id = this.id,
        tag = this.tag,
        label = this.name,
        description = this.description,
        preview = SVGModel(this.previewURL)
    )
}

fun NutrientRecipePOJO.toModel(): NutrientRecipeModel {
    return NutrientRecipeModel(
        id = this.id,
        label = this.name,
        measure = this.nutrientFieldInfo.measure,
        totalWeight = this.totalValue,
        dailyWeight = this.nutrientFieldInfo.dailyAllowance,
        dailyPercent = min(
            (this.totalValue * 100 / this.nutrientFieldInfo.dailyAllowance).toInt(),
            100
        )
    )
}

fun NutrientFilterPOJO.toModelEdit(): NutrientFilterEditModel {
    return NutrientFilterEditModel(
        id = this.id,
        name = this.name,
        measure = this.nutrientFieldInfo.measure,
        rangeMin = this.nutrientFieldInfo.rangeMin,
        rangeMax = this.nutrientFieldInfo.rangeMax,
        minValue = this.minValue,
        maxValue = this.maxValue
    )
}

fun NutrientFilterPOJO.toModelPreview(): NutrientFilterPreviewModel {
    return NutrientFilterPreviewModel(
        id = this.id,
        name = this.name,
        measure = this.nutrientFieldInfo.measure,
        minValue = this.minValue,
        maxValue = this.maxValue,
    )
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