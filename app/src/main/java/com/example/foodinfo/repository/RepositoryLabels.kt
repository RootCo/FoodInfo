package com.example.foodinfo.repository

import com.example.foodinfo.repository.model.CategoryModel
import com.example.foodinfo.repository.model.LabelFilterEditModel
import com.example.foodinfo.repository.model.LabelModel


interface RepositoryLabels {
    fun getLabel(categoryName: String, labelName: String): LabelModel

    fun getLabels(categoryName: String): List<LabelModel>

    fun getCategory(name: String): CategoryModel

    fun getCategories(): List<CategoryModel>

    fun getLabelsFilterEdit(categoryName: String): List<LabelFilterEditModel>
}