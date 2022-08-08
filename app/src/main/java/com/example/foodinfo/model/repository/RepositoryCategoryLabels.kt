package com.example.foodinfo.model.repository

import com.example.foodinfo.model.local.CategoryLabel


interface RepositoryCategoryLabels {
    fun getByLabel(category: String, label: String): CategoryLabel

    fun getAll(): Map<String, List<CategoryLabel>>

    fun getCategory(category: String): List<CategoryLabel>
}