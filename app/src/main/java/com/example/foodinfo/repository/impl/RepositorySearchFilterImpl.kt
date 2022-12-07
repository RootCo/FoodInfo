package com.example.foodinfo.repository.impl

import com.example.foodinfo.local.dao.SearchFilterDAO
import com.example.foodinfo.repository.RepositorySearchFilter
import com.example.foodinfo.repository.mapper.toModel
import com.example.foodinfo.repository.model.RangeFieldModel
import javax.inject.Inject


class RepositorySearchFilterImpl @Inject constructor(
    private val searchFilterDAO: SearchFilterDAO
) : RepositorySearchFilter {

    override fun getField(fieldName: String): RangeFieldModel {
        return searchFilterDAO.getField(fieldName).toModel()
    }

    override fun getFieldsByCategory(categoryName: String): List<RangeFieldModel> {
        return searchFilterDAO.getFieldsByCategory(categoryName).map { it.toModel() }
    }
}