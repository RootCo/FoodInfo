package com.example.foodinfo.repository.mapper

import com.example.foodinfo.local.entity.SearchInputEntity
import com.example.foodinfo.local.model.SearchInputModel


fun SearchInputEntity.toModel(): SearchInputModel {
    return SearchInputModel(
        id = this.id,
        inputText = this.inputText,
        date = this.date.toString() // implement proper conversion
    )
}

fun SearchInputModel.toEntity(): SearchInputEntity {
    return SearchInputEntity(
        inputText = this.inputText,
        date = this.date.toLong() // implement proper conversion
    )
}