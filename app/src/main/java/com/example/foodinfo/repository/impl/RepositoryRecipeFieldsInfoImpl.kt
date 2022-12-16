package com.example.foodinfo.repository.impl

import com.example.foodinfo.local.dao.RecipeFieldsInfoDao
import com.example.foodinfo.repository.RepositoryRecipeFieldsInfo
import com.example.foodinfo.repository.mapper.toModel
import com.example.foodinfo.repository.mapper.toModelHint
import com.example.foodinfo.repository.mapper.toModelSearch
import com.example.foodinfo.repository.model.CategoryFieldModel
import com.example.foodinfo.repository.model.LabelHintModel
import com.example.foodinfo.repository.model.LabelSearchModel
import com.example.foodinfo.repository.model.NutrientHintModel
import javax.inject.Inject


class RepositoryRecipeFieldsInfoImpl @Inject constructor(
    private val recipeFieldsInfoDao: RecipeFieldsInfoDao
) : RepositoryRecipeFieldsInfo {
    override fun getNutrientHint(nutrientName: String): NutrientHintModel {
        return recipeFieldsInfoDao.getNutrient(nutrientName).toModelHint()
    }

    override fun getLabelHint(categoryName: String, labelName: String): LabelHintModel {
        return recipeFieldsInfoDao.getLabel(categoryName, labelName).toModelHint()
    }

    override fun getLabelsSearch(categoryName: String): List<LabelSearchModel> {
        return recipeFieldsInfoDao.getLabels(categoryName).map { it.toModelSearch() }
    }

    override fun getCategory(name: String): CategoryFieldModel {
        return recipeFieldsInfoDao.getCategory(name).toModel()
    }

    override fun getCategories(): List<CategoryFieldModel> {
        return recipeFieldsInfoDao.getCategories().map { it.toModel() }
    }
}