package com.example.foodinfo.repository.mapper

import com.example.foodinfo.local.entity.SearchFilterEntity
import com.example.foodinfo.repository.model.SearchFilterModel


fun SearchFilterEntity.toModel(): SearchFilterModel {
    return SearchFilterModel(id = this.id, name = this.name)
}

fun SearchFilterModel.toEntity(): SearchFilterEntity {
    return SearchFilterEntity(name = this.name)
}