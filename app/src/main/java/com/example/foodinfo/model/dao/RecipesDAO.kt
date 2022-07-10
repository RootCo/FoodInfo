package com.example.foodinfo.model.dao

import com.example.foodinfo.model.entities.RecipeCategoryLabels
import com.example.foodinfo.model.entities.RecipeShort
import com.example.foodinfo.model.entities.SearchFilter


interface RecipesDAO {
    fun getDaily(): List<RecipeShort>

    fun getByCategory(category: String, label: String): List<RecipeShort>

    fun getByFilter(query: String, searchFilter: SearchFilter): List<RecipeShort>

    fun getById(id: String): RecipeShort

    fun getRecipeCategories(): List<RecipeCategoryLabels>
}