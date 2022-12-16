package com.example.foodinfo.repository.impl

import com.example.foodinfo.local.dao.SearchFilterDAO
import com.example.foodinfo.repository.RepositorySearchFilter
import com.example.foodinfo.repository.mapper.*
import com.example.foodinfo.repository.model.*
import com.example.foodinfo.repository.model.filter_field.CategoryFilterField
import com.example.foodinfo.utils.FilterQueryBuilder
import javax.inject.Inject


class RepositorySearchFilterImpl @Inject constructor(
    private val searchFilterDAO: SearchFilterDAO
) : RepositorySearchFilter {

    override fun getQueryByFilter(filterName: String, inputText: String): String {
        val builder = FilterQueryBuilder(
            searchFilterDAO.getBaseFields(filterName).map { it.toModelFilterField() },
            searchFilterDAO.getCategories(filterName).toModelFilterField(),
            searchFilterDAO.getNutrients(filterName).map { it.toModelFilterField() },
        )
        builder.setInputText(inputText)
        return builder.getQuery()
    }

    override fun getQueryByLabel(categoryName: String, labelName: String): String {
        val builder = FilterQueryBuilder(
            categoryFilterFields = listOf(CategoryFilterField(categoryName, listOf(labelName)))
        )
        return builder.getQuery()
    }


    override fun getCategory(filterName: String, categoryName: String): CategoryFilterEditModel {
        return searchFilterDAO.getCategory(filterName, categoryName).toModelFilterEdit().first()
    }

    override fun getCategoriesPreview(filterName: String): List<CategoryFilterPreviewModel> {
        return searchFilterDAO.getCategories(filterName).toModelFilterPreview()
    }

    override fun updateCategory(
        filterName: String, categoryName: String, category: CategoryFilterEditModel
    ) {
        searchFilterDAO.updateCategory(category.labels.map {
            it.toEntity(filterName, categoryName)
        })
    }


    override fun getNutrientsEdit(filterName: String): List<NutrientFilterEditModel> {
        return searchFilterDAO.getNutrients(filterName).map { it.toModelEdit() }
    }

    override fun getNutrientsPreview(filterName: String): List<NutrientFilterPreviewModel> {
        return searchFilterDAO.getNutrients(filterName).map { it.toModelPreview() }
    }

    override fun updateNutrients(filterName: String, nutrients: List<NutrientFilterEditModel>) {
        searchFilterDAO.updateNutrients(nutrients.map { it.toEntity(filterName) })
    }


    override fun getBaseFields(filterName: String): List<BaseFieldFilterEditModel> {
        return searchFilterDAO.getBaseFields(filterName).map { it.toModelEdit() }
    }

    override fun updateBaseFields(filterName: String, fields: List<BaseFieldFilterEditModel>) {
        searchFilterDAO.updateBaseFields(fields.map { it.toEntity(filterName) })
    }
}