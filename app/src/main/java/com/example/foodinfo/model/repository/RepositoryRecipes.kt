package com.example.foodinfo.model.repository

import androidx.paging.PagingData
import com.example.foodinfo.model.local.RecipeExplore
import com.example.foodinfo.model.local.RecipeExtended
import com.example.foodinfo.model.local.RecipeResult
import com.example.foodinfo.model.local.entities.SearchFilter
import kotlinx.coroutines.flow.Flow

interface RepositoryRecipes {
    fun getDaily(): Flow<RecipeExplore>

    fun getPopular(): Flow<PagingData<RecipeExplore>>

    fun getByFilterResult(filter: SearchFilter): List<RecipeResult>

    fun getByFilterExplore(filter: SearchFilter): List<RecipeExplore>

    fun getByFilterExtended(filter: SearchFilter): List<RecipeExtended>

    fun getById(id: String): RecipeExtended
}