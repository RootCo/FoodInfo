package com.example.foodinfo.repository

import com.example.foodinfo.repository.model.RangeFieldModel


interface RepositorySearchFilter {
    fun getField(fieldName: String): RangeFieldModel

    fun getFieldsByCategory(categoryName: String): List<RangeFieldModel>
}