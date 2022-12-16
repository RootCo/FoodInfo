package com.example.foodinfo.repository.mapper

import com.example.foodinfo.local.entity.BaseFieldFilterEntity
import com.example.foodinfo.local.pojo.BaseFieldFilterPOJO
import com.example.foodinfo.repository.model.BaseFieldFilterEditModel
import com.example.foodinfo.repository.model.filter_field.BaseFilterField


fun BaseFieldFilterPOJO.toModelEdit(): BaseFieldFilterEditModel {
    return BaseFieldFilterEditModel(
        id = this.id,
        name = this.name,
        measure = this.fieldInfo.measure,
        stepSize = this.fieldInfo.stepSize,
        rangeMin = this.fieldInfo.rangeMin,
        rangeMax = this.fieldInfo.rangeMax,
        minValue = this.minValue,
        maxValue = this.maxValue
    )
}

fun List<BaseFieldFilterPOJO>.toModelFilterField(): List<BaseFilterField> {
    return this.map { field ->
        BaseFilterField(
            name = field.name,
            minValue = if (field.minValue == field.fieldInfo.rangeMin) null else field.minValue,
            maxValue = if (field.maxValue == field.fieldInfo.rangeMax) null else field.maxValue
        )
    }.filter { !(it.minValue == null && it.maxValue == null) }
}


fun BaseFieldFilterEditModel.toEntity(filterName: String): BaseFieldFilterEntity {
    return BaseFieldFilterEntity(
        id = this.id,
        name = this.name,
        filterName = filterName,
        minValue = this.minValue,
        maxValue = this.maxValue
    )
}