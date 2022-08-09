package com.example.foodinfo.repository

import androidx.paging.PagingData
import com.example.foodinfo.local.model.RecipeExtended
import com.example.foodinfo.local.model.RecipeShort
import com.example.foodinfo.local.model.SearchFilter
import kotlinx.coroutines.flow.Flow

interface RepositoryRecipes {
    fun getPopular(): Flow<PagingData<RecipeShort>>

    fun getByFilterShort(filter: SearchFilter): Flow<PagingData<RecipeShort>>

    fun getByIdExtended(id: String): Flow<RecipeExtended>
}