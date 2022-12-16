package com.example.foodinfo.repository.mapper

import com.example.foodinfo.local.entity.BaseFieldFilterEntity
import com.example.foodinfo.local.entity.BaseFieldFilterPOJO
import com.example.foodinfo.repository.model.BaseFieldFilterEditModel


fun BaseFieldFilterPOJO.toModelEdit(): BaseFieldFilterEditModel {
    return BaseFieldFilterEditModel(
        id = this.id,
        name = this.name,
        measure = this.baseFieldInfo.measure,
        rangeMin = this.baseFieldInfo.rangeMin,
        rangeMax = this.baseFieldInfo.rangeMax,
        minValue = this.minValue,
        maxValue = this.maxValue
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