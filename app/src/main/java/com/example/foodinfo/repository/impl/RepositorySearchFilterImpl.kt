package com.example.foodinfo.repository.impl

import com.example.foodinfo.local.dao.SearchFilterDAO
import com.example.foodinfo.repository.RepositorySearchFilter
import com.example.foodinfo.repository.mapper.toEntity
import com.example.foodinfo.repository.mapper.toModel
import com.example.foodinfo.repository.model.FilterNutrientModel
import com.example.foodinfo.repository.model.RangeFieldModel
import com.example.foodinfo.repository.model.SearchFilterModel
import com.example.foodinfo.utils.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class RepositorySearchFilterImpl @Inject constructor(
    private val searchFilterDAO: SearchFilterDAO
) : RepositorySearchFilter {

    override suspend fun getRangeField(fieldName: String): RangeFieldModel {
        return searchFilterDAO.getRangeField(fieldName).toModel()
    }

    override suspend fun getRangeFieldsByCategory(categoryName: String): List<RangeFieldModel> {
        return searchFilterDAO.getRangeFieldsByCategory(categoryName).map { it.toModel() }
    }

    override suspend fun getEditedNutrients(): List<FilterNutrientModel> {
        return listOf(
            FilterNutrientModel(1, "Protein", "g", 70f, 100f),
            FilterNutrientModel(2, "Carbs", "g", 8.3f, 32.2f),
            FilterNutrientModel(3, "Fat", "g", 24f, 74f),
            FilterNutrientModel(4, "Vitamin A", "mg", 3.5f, 17.6f),
            FilterNutrientModel(6, "Carbohydrates (net)", "ug", 100f, 450f)
        )
    }

    override fun getFilter(filterName: String): Flow<State<SearchFilterModel>> {
        return flow {
            emit(State.Loading())
            searchFilterDAO.getFilter(filterName).collect {
                emit(State.Success(it.toModel()))
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun updateFilter(filter: SearchFilterModel) {
        searchFilterDAO.updateFilter(filter.toEntity())
    }
}