package com.example.foodinfo.repository

import com.example.foodinfo.local.entity.SearchFilterEntity
import com.example.foodinfo.repository.model.FilterNutrientModel
import com.example.foodinfo.repository.model.RangeFieldModel
import com.example.foodinfo.repository.model.SearchFilterModel
import com.example.foodinfo.utils.State
import kotlinx.coroutines.flow.Flow


interface RepositorySearchFilter {
    suspend fun getRangeField(fieldName: String): RangeFieldModel

    suspend fun getRangeFieldsByCategory(categoryName: String): List<RangeFieldModel>

    suspend fun getEditedNutrients(): List<FilterNutrientModel>

    fun getFilter(filterName: String = SearchFilterEntity.DEFAULT_FILTER_NAME): Flow<State<SearchFilterModel>>

    fun updateFilter(filter: SearchFilterModel)
}