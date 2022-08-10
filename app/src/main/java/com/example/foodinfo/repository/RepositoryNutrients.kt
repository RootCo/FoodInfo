package com.example.foodinfo.repository

import com.example.foodinfo.local.model.NutrientModel


interface RepositoryNutrients {
    fun getByLabel(label: String): NutrientModel

    fun getAll(): List<NutrientModel>
}