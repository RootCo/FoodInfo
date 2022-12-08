package com.example.foodinfo.repository

import com.example.foodinfo.repository.model.RangeFieldModel


interface RepositorySearchFilter {
    suspend fun getField(fieldName: String): RangeFieldModel

    suspend fun getFieldsByCategory(categoryName: String): List<RangeFieldModel>
}