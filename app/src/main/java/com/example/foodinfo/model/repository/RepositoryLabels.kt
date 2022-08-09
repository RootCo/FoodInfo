package com.example.foodinfo.model.repository

import com.example.foodinfo.model.local.Label


interface RepositoryLabels {
    fun getByLabel(category: String, label: String): Label

    fun getAll(): Map<String, List<Label>>

    fun getCategory(category: String): List<Label>
}