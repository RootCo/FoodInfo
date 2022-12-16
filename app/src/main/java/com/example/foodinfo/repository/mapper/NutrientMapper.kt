package com.example.foodinfo.repository.mapper

import com.example.foodinfo.local.entity.NutrientFieldEntity
import com.example.foodinfo.local.entity.NutrientFilterEntity
import com.example.foodinfo.local.pojo.NutrientFilterPOJO
import com.example.foodinfo.local.pojo.NutrientRecipePOJO
import com.example.foodinfo.repository.model.*
import com.example.foodinfo.repository.model.filter_field.NutrientFilterField
import java.lang.Integer.min


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
        dailyPercent = min(
            (this.totalValue * 100 / this.fieldInfo.dailyAllowance).toInt(),
            100
        )
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

fun NutrientFilterPOJO.toModelPreview(): NutrientFilterPreviewModel {
    return NutrientFilterPreviewModel(
        id = this.id,
        name = this.name,
        measure = this.fieldInfo.measure,
        minValue = this.minValue,
        maxValue = this.maxValue,
    )
}

// set value to null if it's same as range affects on query. See FilterQueryBuilder.rangeFieldToQuery() for details
fun NutrientFilterPOJO.toModelFilterField(): NutrientFilterField {
    return NutrientFilterField(
        name = this.name,
        minValue = if (this.minValue == this.fieldInfo.rangeMin) null else this.minValue,
        maxValue = if (this.maxValue == this.fieldInfo.rangeMax) null else this.maxValue
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