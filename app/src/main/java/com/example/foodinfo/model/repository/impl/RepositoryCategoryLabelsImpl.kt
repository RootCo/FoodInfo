package com.example.foodinfo.model.repository.impl

import com.example.foodinfo.model.local.CategoryLabel
import com.example.foodinfo.model.local.dao.CategoryLabelsDAO
import com.example.foodinfo.model.repository.RepositoryCategoryLabels
import javax.inject.Inject


class RepositoryCategoryLabelsImpl @Inject constructor(
    private val categoryLabelsDAO: CategoryLabelsDAO
) : RepositoryCategoryLabels {
    override fun getByLabel(category: String, label: String): CategoryLabel {
        return CategoryLabel.fromEntity(categoryLabelsDAO.getByLabel(category, label))
    }

    override fun getAll(): Map<String, List<CategoryLabel>> {
        return categoryLabelsDAO.getAll().map { entity ->
            CategoryLabel.fromEntity(entity)
        }.groupBy { it.category }
    }

    override fun getCategory(category: String): List<CategoryLabel> {
        return categoryLabelsDAO.getCategory(category).map { entity ->
            CategoryLabel.fromEntity(entity)
        }
    }
}