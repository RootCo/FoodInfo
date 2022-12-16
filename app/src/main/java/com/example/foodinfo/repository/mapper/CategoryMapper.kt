package com.example.foodinfo.repository.mapper

import com.example.foodinfo.local.entity.CategoryFieldEntity
import com.example.foodinfo.local.entity.LabelFilterEntity
import com.example.foodinfo.local.entity.LabelRecipeEntity
import com.example.foodinfo.repository.model.*
import com.example.foodinfo.repository.model.filter_field.CategoryFilterField


fun CategoryFieldEntity.toModel(): CategoryFieldModel {
    return CategoryFieldModel(
        id = this.id,
        name = this.name,
        preview = SVGModel(this.previewURL)
    )
}

fun List<LabelRecipeEntity>.toModelRecipe(): List<CategoryRecipeModel> {
    return this.groupBy { label -> label.category }.entries.map { category ->
        CategoryRecipeModel(
            name = category.key,
            labels = category.value.map { it.toModelShort() }
        )
    }
}

fun List<LabelFilterEntity>.toModelFilterEdit(): List<CategoryFilterEditModel> {
    return this.groupBy { label -> label.category }.entries.map { category ->
        CategoryFilterEditModel(
            name = category.key,
            labels = category.value.map { it.toModelEdit() }
        )
    }
}

fun List<LabelFilterEntity>.toModelFilterPreview(): List<CategoryFilterPreviewModel> {
    return this.groupBy { label -> label.category }.entries.map { category ->
        CategoryFilterPreviewModel(
            name = category.key,
            labels = category.value.map { it.toModelShort() }
        )
    }
}

fun List<LabelFilterEntity>.toModelFilterField(): List<CategoryFilterField> {
    return this.groupBy { label -> label.category }.entries.map { category ->
        CategoryFilterField(
            name = category.key,
            labels = category.value.filter { it.isSelected }.map { it.name }
        )
    }
}