package com.example.foodinfo.model.repository

import com.example.foodinfo.model.local.RecipeExplore
import com.example.foodinfo.model.local.RecipeExtended
import com.example.foodinfo.model.local.RecipeResult
import com.example.foodinfo.model.local.entities.SearchFilter

interface RepositoryRecipes {
    fun getDaily(): RecipeExplore

    fun getPopular(): List<RecipeExplore>

    fun getByFilterResult(filter: SearchFilter): List<RecipeResult>

    fun getByFilterExplore(filter: SearchFilter): List<RecipeExplore>

    fun getByFilterExtended(filter: SearchFilter): List<RecipeExtended>

    fun getById(id: String): RecipeExtended
}