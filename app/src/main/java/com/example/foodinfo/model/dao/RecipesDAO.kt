package com.example.foodinfo.model.dao

import androidx.sqlite.db.SupportSQLiteQuery
import com.example.foodinfo.model.entities.RecipeCategoryLabels
import com.example.foodinfo.model.entities.RecipeShort


interface RecipesDAO {
    fun getDaily(): List<RecipeShort>

    fun getByFilter(query: SupportSQLiteQuery): List<RecipeShort>

    fun getById(id: String): RecipeShort

    fun getRecipeCategories(): List<RecipeCategoryLabels>
}