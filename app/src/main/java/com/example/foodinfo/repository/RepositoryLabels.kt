package com.example.foodinfo.repository

import com.example.foodinfo.repository.model.LabelModel


interface RepositoryLabels {
    fun getByLabel(category: String, label: String): LabelModel

    fun getAll(): Map<String, List<LabelModel>>

    fun getCategory(category: String): List<LabelModel>
}