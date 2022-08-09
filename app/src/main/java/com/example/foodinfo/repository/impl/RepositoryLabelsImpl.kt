package com.example.foodinfo.repository.impl

import com.example.foodinfo.local.model.Label
import com.example.foodinfo.local.dao.LabelsDAO
import com.example.foodinfo.repository.RepositoryLabels
import javax.inject.Inject


class RepositoryLabelsImpl @Inject constructor(
    private val labelsDAO: LabelsDAO
) : RepositoryLabels {
    override fun getByLabel(category: String, label: String): Label {
        return Label.fromEntity(labelsDAO.getByLabel(category, label))
    }

    override fun getAll(): Map<String, List<Label>> {
        return labelsDAO.getAll().map { entity ->
            Label.fromEntity(entity)
        }.groupBy { it.category }
    }

    override fun getCategory(category: String): List<Label> {
        return labelsDAO.getCategory(category).map { entity ->
            Label.fromEntity(entity)
        }
    }
}