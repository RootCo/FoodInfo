package com.example.foodinfo.repository

import com.example.foodinfo.local.entity.SearchFilterEntity
import com.example.foodinfo.repository.model.*


interface RepositorySearchFilter {

    fun getQueryByFilter(
        filterName: String = SearchFilterEntity.DEFAULT_NAME,
        inputText: String = ""
    ): String

    fun getQueryByLabel(categoryName: String, labelName: String): String


    fun getCategory(filterName: String, categoryName: String): CategoryFilterEditModel

    fun getCategoriesPreview(filterName: String): List<CategoryFilterPreviewModel>

    fun updateCategory(filterName: String, categoryName: String, category: CategoryFilterEditModel)


    fun getNutrientsEdit(filterName: String): List<NutrientFilterEditModel>

    fun getNutrientsPreview(filterName: String): List<NutrientFilterPreviewModel>

    fun updateNutrients(filterName: String, nutrients: List<NutrientFilterEditModel>)


    fun getBaseFields(filterName: String): List<BaseFieldFilterEditModel>

    fun updateBaseFields(filterName: String, fields: List<BaseFieldFilterEditModel>)
}