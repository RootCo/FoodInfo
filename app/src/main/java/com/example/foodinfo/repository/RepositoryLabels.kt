package com.example.foodinfo.repository

import com.example.foodinfo.local.model.Label


interface RepositoryLabels {
    fun getByLabel(category: String, label: String): Label

    fun getAll(): Map<String, List<Label>>

    fun getCategory(category: String): List<Label>
}