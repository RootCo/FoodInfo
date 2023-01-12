package com.example.foodinfo.repository.mapper

import com.example.foodinfo.local.entity.BaseFieldEntity
import com.example.foodinfo.local.entity.BaseFieldFilterEntity
import com.example.foodinfo.repository.model.BaseFieldFilterEditModel
import com.example.foodinfo.repository.model.BaseFieldModel
import com.example.foodinfo.repository.model.filter_field.BaseFilterField


fun BaseFieldFilterEntity.toModelEdit(): BaseFieldFilterEditModel {
    return BaseFieldFilterEditModel(
        id = this.id,
        fieldInfo = this.fieldInfo.toModel(),
        minValue = this.minValue,
        maxValue = this.maxValue
    )
}

//TODO уточнить надо ли вообще этот мапинг
fun List<BaseFieldFilterEntity>.toModelFilterField(): List<BaseFilterField> {
    return this.map { field ->
        BaseFilterField(
            name = field.fieldInfo.name,
            minValue = if (field.minValue == field.fieldInfo.rangeMin) null else field.minValue,
            maxValue = if (field.maxValue == field.fieldInfo.rangeMax) null else field.maxValue
        )
    }.filter { !(it.minValue == null && it.maxValue == null) }
}


fun BaseFieldFilterEditModel.toEntity(filterName: String): BaseFieldFilterEntity {
    return BaseFieldFilterEntity(
        id = this.id,
        fieldInfo = this.fieldInfo.toEntity(),
        filterName = filterName,
        minValue = this.minValue,
        maxValue = this.maxValue
    )
}

fun BaseFieldModel.toEntity(): BaseFieldEntity {
    return BaseFieldEntity(
        id = this.id,
        name = this.name,
        measure = this.measure,
        rangeMin = this.rangeMin,
        rangeMax = this.rangeMax,
        stepSize = this.stepSize
    )
}

fun BaseFieldEntity.toModel(): BaseFieldModel {
    return BaseFieldModel(
        id = this.id,
        name = this.name,
        measure = this.measure,
        rangeMin = this.rangeMin,
        rangeMax = this.rangeMax,
        stepSize = this.stepSize
    )
}