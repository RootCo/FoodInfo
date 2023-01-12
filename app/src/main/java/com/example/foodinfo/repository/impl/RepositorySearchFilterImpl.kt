package com.example.foodinfo.repository.impl

import com.example.foodinfo.local.dao.RecipeFieldsInfoDao
import com.example.foodinfo.local.dao.SearchFilterDAO
import com.example.foodinfo.local.entity.BaseFieldFilterEntity
import com.example.foodinfo.local.entity.LabelFilterEntity
import com.example.foodinfo.local.entity.NutrientFilterEntity
import com.example.foodinfo.repository.RepositorySearchFilter
import com.example.foodinfo.repository.mapper.toModelEdit
import com.example.foodinfo.repository.mapper.toModelFilterEdit
import com.example.foodinfo.repository.mapper.toModelFilterField
import com.example.foodinfo.repository.model.CategoryFilterEditModel
import com.example.foodinfo.repository.model.NutrientFilterEditModel
import com.example.foodinfo.repository.model.SearchFilterEditModel
import com.example.foodinfo.repository.model.filter_field.CategoryFilterField
import com.example.foodinfo.utils.FilterQueryBuilder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class RepositorySearchFilterImpl @Inject constructor(
    private val searchFilterDAO: SearchFilterDAO,
    private val recipeFieldsInfoDao: RecipeFieldsInfoDao
) : RepositorySearchFilter {

    override fun getQueryByFilter(filterName: String, inputText: String): String {
        val builder = FilterQueryBuilder(
            searchFilterDAO.getBaseFields(filterName).toModelFilterField(),
            searchFilterDAO.getLabelsAll(filterName).toModelFilterField(),
            searchFilterDAO.getNutrients(filterName).toModelFilterField(),
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


    override fun getCategoryEdit(filterName: String, categoryName: String): Flow<CategoryFilterEditModel> {
        return searchFilterDAO.observeLabelsCategory(filterName, categoryName).map { data ->
            data.toModelFilterEdit().first()
        }
    }

    override fun getNutrientsEdit(filterName: String): Flow<List<NutrientFilterEditModel>> {
        return searchFilterDAO.observeNutrients(filterName).map { data ->
            data.map { it.toModelEdit() }
        }
    }

    override fun getFilterEdit(filterName: String): Flow<SearchFilterEditModel> {
        return searchFilterDAO.observeFilter(filterName).map { it.toModelEdit() }
    }


    override fun createFilter(filterName: String) {
        val baseFields = recipeFieldsInfoDao.getBaseFields().map { field ->
            BaseFieldFilterEntity(
                filterName = filterName,
                fieldInfo = field,
                minValue = field.rangeMin,
                maxValue = field.rangeMax
            )
        }
        val nutrients = recipeFieldsInfoDao.getNutrientFields().map { nutrient ->
            NutrientFilterEntity(
                filterName = filterName,
                fieldInfo = nutrient,
                minValue = nutrient.rangeMin,
                maxValue = nutrient.rangeMax
            )
        }
        val labels = recipeFieldsInfoDao.getLabelFields().map { label ->
            LabelFilterEntity(
                filterName = filterName,
                category = label.category,
                name = label.name,
                isSelected = false
            )
        }
        searchFilterDAO.initializeFilter(filterName, labels, nutrients, baseFields)
    }

    override fun resetFilter(filterName: String) {
        resetBaseFields(filterName)
        resetNutrients(filterName)
        resetLabels(filterName)
    }


    override fun resetBaseFields(filterName: String) {
        val baseFields = searchFilterDAO.getBaseFields(filterName).map { field ->
            BaseFieldFilterEntity(
                id = field.id,
                filterName = filterName,
                fieldInfo = field.fieldInfo,
                minValue = field.fieldInfo.rangeMin,
                maxValue = field.fieldInfo.rangeMax
            )
        }
        searchFilterDAO.updateBaseFields(baseFields)
    }

    override fun resetNutrients(filterName: String) {
        val nutrients = searchFilterDAO.getNutrients(filterName).map { nutrient ->
            NutrientFilterEntity(
                id = nutrient.id,
                filterName = filterName,
                fieldInfo = nutrient.fieldInfo,
                minValue = nutrient.fieldInfo.rangeMin,
                maxValue = nutrient.fieldInfo.rangeMax
            )
        }
        searchFilterDAO.updateNutrients(nutrients)
    }

    override fun resetCategory(filterName: String, categoryName: String) {
        val labels = searchFilterDAO.getLabelsCategory(filterName, categoryName).map { label ->
            LabelFilterEntity(
                id = label.id,
                name = label.name,
                category = label.category,
                filterName = filterName,
                isSelected = false
            )
        }
        searchFilterDAO.updateLabels(labels)
    }

    private fun resetLabels(filterName: String) {
        val labels = searchFilterDAO.getLabelsAll(filterName).map { label ->
            LabelFilterEntity(
                id = label.id,
                name = label.name,
                category = label.category,
                filterName = filterName,
                isSelected = false
            )
        }
        searchFilterDAO.updateLabels(labels)
    }


    override fun updateBaseField(id: Long, minValue: Float, maxValue: Float) {
        searchFilterDAO.updateBaseField(id, minValue, maxValue)
    }

    override fun updateNutrient(id: Long, minValue: Float, maxValue: Float) {
        searchFilterDAO.updateNutrient(id, minValue, maxValue)
    }

    override fun updateLabel(id: Long, isSelected: Boolean) {
        searchFilterDAO.updateLabel(id, isSelected)
    }
}