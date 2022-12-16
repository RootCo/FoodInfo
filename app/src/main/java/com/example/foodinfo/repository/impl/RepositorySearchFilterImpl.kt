package com.example.foodinfo.repository.impl

import com.example.foodinfo.local.dao.RecipeFieldsInfoDao
import com.example.foodinfo.local.dao.SearchFilterDAO
import com.example.foodinfo.local.entity.BaseFieldFilterEntity
import com.example.foodinfo.local.entity.LabelFilterEntity
import com.example.foodinfo.local.entity.NutrientFilterEntity
import com.example.foodinfo.repository.RepositorySearchFilter
import com.example.foodinfo.repository.mapper.*
import com.example.foodinfo.repository.model.*
import com.example.foodinfo.repository.model.filter_field.CategoryFilterField
import com.example.foodinfo.utils.FilterQueryBuilder
import javax.inject.Inject


class RepositorySearchFilterImpl @Inject constructor(
    private val searchFilterDAO: SearchFilterDAO,
    private val recipeFieldsInfoDao: RecipeFieldsInfoDao
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


    override fun createFilter(filterName: String) {
        val labels = recipeFieldsInfoDao.getLabelFields().map { label ->
            LabelFilterEntity(
                filterName = filterName,
                category = label.category,
                name = label.name,
                isSelected = false
            )
        }
        val nutrients = recipeFieldsInfoDao.getNutrientFields().map { nutrient ->
            NutrientFilterEntity(
                filterName = filterName,
                name = nutrient.name,
                minValue = nutrient.rangeMin,
                maxValue = nutrient.rangeMax
            )
        }
        val baseFields = recipeFieldsInfoDao.getBaseFields().map { nutrient ->
            BaseFieldFilterEntity(
                filterName = filterName,
                name = nutrient.name,
                minValue = nutrient.rangeMin,
                maxValue = nutrient.rangeMax
            )
        }
        searchFilterDAO.initializeFilter(filterName, labels, nutrients, baseFields)
    }

    // same function with different names for more clarity when using them
    override fun clearFilter(filterName: String) {
        createFilter(filterName)
    }
}