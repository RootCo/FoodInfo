package com.example.foodinfo.repository

import com.example.foodinfo.repository.model.FilterNutrientModel
import com.example.foodinfo.repository.model.RangeFieldModel


interface RepositorySearchFilter {
    suspend fun getRangeField(fieldName: String): RangeFieldModel

    suspend fun getRangeFieldsByCategory(categoryName: String): List<RangeFieldModel>

    suspend fun getEditedNutrients(): List<FilterNutrientModel>
}