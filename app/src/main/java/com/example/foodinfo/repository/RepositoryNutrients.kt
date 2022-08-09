package com.example.foodinfo.repository

import com.example.foodinfo.local.model.Nutrient


interface RepositoryNutrients {
    fun getByLabel(label: String): Nutrient

    fun getAll(): List<Nutrient>
}