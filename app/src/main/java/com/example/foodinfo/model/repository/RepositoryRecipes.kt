package com.example.foodinfo.model.repository

import com.example.foodinfo.model.local.RecipeFull
import com.example.foodinfo.model.local.RecipeShort
import com.example.foodinfo.model.local.entities.SearchFilter

interface RepositoryRecipes {
    fun getDaily(): List<RecipeShort>

    fun getByFilterShort(filter: SearchFilter): List<RecipeShort>

    fun getByFilterFull(filter: SearchFilter): List<RecipeFull>

    fun getById(id: String): RecipeShort
}