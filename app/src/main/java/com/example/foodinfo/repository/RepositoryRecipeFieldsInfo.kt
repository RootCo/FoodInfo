package com.example.foodinfo.repository

import com.example.foodinfo.repository.model.CategoryFieldModel
import com.example.foodinfo.repository.model.LabelHintModel
import com.example.foodinfo.repository.model.LabelSearchModel
import com.example.foodinfo.repository.model.NutrientHintModel


interface RepositoryRecipeFieldsInfo {

    fun getNutrientHint(nutrientName: String): NutrientHintModel

    fun getLabelHint(categoryName: String, labelName: String): LabelHintModel

    fun getLabelsSearch(categoryName: String): List<LabelSearchModel>

    fun getCategory(name: String): CategoryFieldModel

    fun getCategories(): List<CategoryFieldModel>
}