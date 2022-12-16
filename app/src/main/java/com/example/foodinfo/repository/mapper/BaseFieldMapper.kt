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

// set value to null if it's same as range affects on query. See FilterQueryBuilder.rangeFieldToQuery() for details
fun BaseFieldFilterPOJO.toModelFilterField(): BaseFilterField {
    return BaseFilterField(
        name = this.name,
        minValue = if (this.minValue == this.fieldInfo.rangeMin) null else this.minValue,
        maxValue = if (this.maxValue == this.fieldInfo.rangeMax) null else this.maxValue
    )
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