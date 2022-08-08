package com.example.foodinfo.model.repository

import com.example.foodinfo.model.local.Nutrient


interface RepositoryNutrients {
    fun getByLabel(label: String): Nutrient

    fun getAll(): List<Nutrient>
}