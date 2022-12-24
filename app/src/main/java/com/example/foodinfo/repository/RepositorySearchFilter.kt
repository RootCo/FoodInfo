package com.example.foodinfo.repository

import com.example.foodinfo.local.entity.SearchFilterEntity
import com.example.foodinfo.repository.model.CategoryFilterEditModel
import com.example.foodinfo.repository.model.NutrientFilterEditModel
import com.example.foodinfo.repository.model.SearchFilterEditModel
import kotlinx.coroutines.flow.Flow


interface RepositorySearchFilter {

    fun getQueryByFilter(
        filterName: String = SearchFilterEntity.DEFAULT_NAME,
        inputText: String = ""
    ): String

    fun getQueryByLabel(categoryName: String, labelName: String): String


    fun getCategoryEdit(
        filterName: String = SearchFilterEntity.DEFAULT_NAME,
        categoryName: String
    ): Flow<CategoryFilterEditModel>

    fun getNutrientsEdit(
        filterName: String = SearchFilterEntity.DEFAULT_NAME
    ): Flow<List<NutrientFilterEditModel>>

    fun getFilterEdit(
        filterName: String = SearchFilterEntity.DEFAULT_NAME
    ): Flow<SearchFilterEditModel>


    fun createFilter(filterName: String = SearchFilterEntity.DEFAULT_NAME)

    fun resetFilter(filterName: String = SearchFilterEntity.DEFAULT_NAME)

    fun resetBaseFields(filterName: String = SearchFilterEntity.DEFAULT_NAME)

    fun resetNutrients(filterName: String = SearchFilterEntity.DEFAULT_NAME)

    fun resetCategory(filterName: String = SearchFilterEntity.DEFAULT_NAME, categoryName: String)


    fun updateBaseField(id: Long, minValue: Float, maxValue: Float)

    fun updateNutrient(id: Long, minValue: Float, maxValue: Float)

    fun updateLabel(id: Long, isSelected: Boolean)
}