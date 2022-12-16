package com.example.foodinfo.repository

import com.example.foodinfo.repository.model.CategoryModel
import com.example.foodinfo.repository.model.LabelModel
import com.example.foodinfo.repository.model.LabelSearchModel


interface RepositoryLabels {
    fun getLabel(categoryName: String, labelName: String): LabelModel

    fun getLabels(categoryName: String): List<LabelModel>

    fun getLabelsSearch(categoryName: String): List<LabelSearchModel>

    fun getCategory(name: String): CategoryModel

    fun getCategories(): List<CategoryModel>
}