package com.example.foodinfo.repository.mapper

import com.example.foodinfo.local.entity.CategoryEntity
import com.example.foodinfo.local.entity.LabelFilterEntity
import com.example.foodinfo.local.entity.LabelRecipeEntity
import com.example.foodinfo.repository.model.*


fun CategoryEntity.toModel(): CategoryModel {
    return CategoryModel(
        id = this.id,
        name = this.name,
        preview = SVGModel(this.previewURL)
    )
}

fun List<LabelRecipeEntity>.toModelRecipe(): List<CategoryRecipeModel> {
    return this.groupBy { labelEntity ->
        labelEntity.category
    }.entries.map { entry ->
        CategoryRecipeModel(entry.key, entry.value.map { it.toModelShort() })
    }
}

fun List<LabelFilterEntity>.toModelFilterEdit(): List<CategoryFilterEditModel> {
    return this.groupBy { labelEntity ->
        labelEntity.category
    }.entries.map { entry ->
        CategoryFilterEditModel(entry.key, entry.value.map { it.toModelEdit() })
    }
}

fun List<LabelFilterEntity>.toModelFilterPreview(): List<CategoryFilterPreviewModel> {
    return this.groupBy { labelEntity ->
        labelEntity.category
    }.entries.map { entry ->
        CategoryFilterPreviewModel(entry.key, entry.value.map { it.toModelShort() })
    }
}