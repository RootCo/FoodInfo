package com.example.foodinfo.model.repository

import com.example.foodinfo.model.entities.RecipeCategoryLabels
import com.example.foodinfo.model.entities.RecipeShort
import com.example.foodinfo.model.entities.SearchFilter

interface RepositoryRecipes {
    fun getDaily(): List<RecipeShort>

    fun getByFilter(filter: SearchFilter): List<RecipeShort>

    fun getById(id: String): RecipeShort

    fun getCategories(): List<RecipeCategoryLabels>
}