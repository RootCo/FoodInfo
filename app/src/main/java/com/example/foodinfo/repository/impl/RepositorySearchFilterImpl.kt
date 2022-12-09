package com.example.foodinfo.repository.impl

import com.example.foodinfo.local.dao.SearchFilterDAO
import com.example.foodinfo.repository.RepositorySearchFilter
import com.example.foodinfo.repository.mapper.toModel
import com.example.foodinfo.repository.model.FilterNutrientModel
import com.example.foodinfo.repository.model.RangeFieldModel
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
}