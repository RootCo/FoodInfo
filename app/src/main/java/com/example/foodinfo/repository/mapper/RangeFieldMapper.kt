package com.example.foodinfo.repository.mapper

import com.example.foodinfo.local.entity.RangeFieldEntity
import com.example.foodinfo.repository.model.RangeFieldModel


fun RangeFieldEntity.toModel(): RangeFieldModel {
    return RangeFieldModel(
        id = this.id,
        name = this.name,
        measure = this.measure,
        category = this.category,
        stepSize = this.step,
        minValue = this.minValue,
        maxValue = this.maxValue,
        minCurrent = this.minValue,
        maxCurrent = this.maxValue
    )
}