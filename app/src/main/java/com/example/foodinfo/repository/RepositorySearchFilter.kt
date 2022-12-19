package com.example.foodinfo.repository

import com.example.foodinfo.local.entity.SearchFilterEntity
import com.example.foodinfo.repository.model.*


interface RepositorySearchFilter {

    fun getQueryByFilter(
        filterName: String = SearchFilterEntity.DEFAULT_NAME,
        inputText: String = ""
    ): String

    fun getQueryByLabel(categoryName: String, labelName: String): String


    fun getCategoryEdit(
        filterName: String = SearchFilterEntity.DEFAULT_NAME,
        categoryName: String
    ): CategoryFilterEditModel

    fun getCategoriesPreview(
        filterName: String = SearchFilterEntity.DEFAULT_NAME
    ): List<CategoryFilterPreviewModel>


    fun getNutrientsEdit(
        filterName: String = SearchFilterEntity.DEFAULT_NAME
    ): List<NutrientFilterEditModel>

    fun getNutrientsPreview(
        filterName: String = SearchFilterEntity.DEFAULT_NAME
    ): List<NutrientFilterPreviewModel>


    fun getBaseFieldsEdit(
        filterName: String = SearchFilterEntity.DEFAULT_NAME
    ): List<BaseFieldFilterEditModel>


    fun createFilter(filterName: String = SearchFilterEntity.DEFAULT_NAME)

    fun resetFilter(filterName: String = SearchFilterEntity.DEFAULT_NAME)

    fun resetBaseFields(filterName: String = SearchFilterEntity.DEFAULT_NAME)

    fun resetNutrients(filterName: String = SearchFilterEntity.DEFAULT_NAME)

    fun resetCategory(filterName: String = SearchFilterEntity.DEFAULT_NAME, categoryName: String)


    fun updateBaseField(id: Long, minValue: Float, maxValue: Float)

    fun updateNutrient(id: Long, minValue: Float, maxValue: Float)

    fun updateLabel(id: Long, isSelected: Boolean)
}