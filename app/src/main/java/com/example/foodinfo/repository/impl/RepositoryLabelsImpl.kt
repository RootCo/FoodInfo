package com.example.foodinfo.repository.impl

import com.example.foodinfo.local.dao.LabelsDAO
import com.example.foodinfo.repository.RepositoryLabels
import com.example.foodinfo.repository.mapper.toModel
import com.example.foodinfo.repository.mapper.toModelSearch
import com.example.foodinfo.repository.model.CategoryModel
import com.example.foodinfo.repository.model.LabelModel
import com.example.foodinfo.repository.model.LabelSearchModel
import javax.inject.Inject


class RepositoryLabelsImpl @Inject constructor(
    private val labelsDAO: LabelsDAO
) : RepositoryLabels {
    override fun getLabel(categoryName: String, labelName: String): LabelModel {
        return labelsDAO.getLabel(categoryName, labelName).toModel()
    }

    override fun getLabels(categoryName: String): List<LabelModel> {
        return labelsDAO.getLabels(categoryName).map { it.toModel() }
    }

    override fun getLabelsSearch(categoryName: String): List<LabelSearchModel> {
        return labelsDAO.getLabels(categoryName).map { it.toModelSearch() }
    }

    override fun getCategory(name: String): CategoryModel {
        return labelsDAO.getCategory(name).toModel()
    }

    override fun getCategories(): List<CategoryModel> {
        return labelsDAO.getCategories().map { it.toModel() }
    }
}