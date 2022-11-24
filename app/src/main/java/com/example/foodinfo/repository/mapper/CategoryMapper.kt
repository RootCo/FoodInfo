package com.example.foodinfo.repository.mapper

import com.example.foodinfo.local.entity.CategoryEntity
import com.example.foodinfo.repository.model.CategoryModel
import com.example.foodinfo.repository.model.SVGModel


fun CategoryEntity.toModel(): CategoryModel {
    return CategoryModel(
        id = this.id,
        name = this.name,
        description = this.description,
        preview = SVGModel(this.previewURL)
    )
}