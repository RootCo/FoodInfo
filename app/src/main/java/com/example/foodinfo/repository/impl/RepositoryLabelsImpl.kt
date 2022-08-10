package com.example.foodinfo.repository.impl

import com.example.foodinfo.local.dao.LabelsDAO
import com.example.foodinfo.local.model.LabelModel
import com.example.foodinfo.repository.RepositoryLabels
import com.example.foodinfo.repository.mapper.toModel
import javax.inject.Inject


class RepositoryLabelsImpl @Inject constructor(
    private val labelsDAO: LabelsDAO
) : RepositoryLabels {
    override fun getByLabel(category: String, label: String): LabelModel {
        return labelsDAO.getByLabel(category, label).toModel()
    }

    override fun getAll(): Map<String, List<LabelModel>> {
        return labelsDAO.getAll().map { it.toModel() }.groupBy { it.category }
    }

    override fun getCategory(category: String): List<LabelModel> {
        return labelsDAO.getCategory(category).map { it.toModel() }
    }
}