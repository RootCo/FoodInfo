package com.example.foodinfo.repository

import com.example.foodinfo.local.entity.SearchFilterEntity
import com.example.foodinfo.repository.model.*


interface RepositorySearchFilter {

    fun getQueryByFilter(
        filterName: String = SearchFilterEntity.DEFAULT_NAME,
        inputText: String = ""
    ): String

    fun getQueryByLabel(categoryName: String, labelName: String): String


    fun getCategory(
        filterName: String = SearchFilterEntity.DEFAULT_NAME, categoryName: String
    ): CategoryFilterEditModel

    fun getCategoriesPreview(
        filterName: String = SearchFilterEntity.DEFAULT_NAME
    ): List<CategoryFilterPreviewModel>

    fun updateCategory(
        filterName: String = SearchFilterEntity.DEFAULT_NAME,
        categoryName: String,
        category: CategoryFilterEditModel
    )


    fun getNutrientsEdit(
        filterName: String = SearchFilterEntity.DEFAULT_NAME
    ): List<NutrientFilterEditModel>

    fun getNutrientsPreview(
        filterName: String = SearchFilterEntity.DEFAULT_NAME
    ): List<NutrientFilterPreviewModel>

    fun updateNutrients(
        filterName: String = SearchFilterEntity.DEFAULT_NAME,
        nutrients: List<NutrientFilterEditModel>
    )


    fun getBaseFields(
        filterName: String = SearchFilterEntity.DEFAULT_NAME
    ): List<BaseFieldFilterEditModel>

    fun updateBaseFields(
        filterName: String = SearchFilterEntity.DEFAULT_NAME, fields: List<BaseFieldFilterEditModel>
    )


    fun createFilter(filterName: String = SearchFilterEntity.DEFAULT_NAME)

    fun clearFilter(filterName: String = SearchFilterEntity.DEFAULT_NAME)
}