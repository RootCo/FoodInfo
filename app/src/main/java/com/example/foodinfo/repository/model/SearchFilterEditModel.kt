package com.example.foodinfo.repository.model

import com.example.foodinfo.local.entity.SearchFilterEntity


data class SearchFilterEditModel(
    val id: Long = 0,
    val name: String = SearchFilterEntity.DEFAULT_NAME,
    val baseFields: List<BaseFieldFilterEditModel>,
    val categories: List<CategoryFilterPreviewModel>,
    val nutrients: List<NutrientFilterPreviewModel>
)