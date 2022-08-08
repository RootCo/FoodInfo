package com.example.foodinfo.model.repository

import androidx.paging.PagingData
import com.example.foodinfo.model.local.RecipeExtended
import com.example.foodinfo.model.local.RecipeShort
import com.example.foodinfo.model.local.SearchFilter
import kotlinx.coroutines.flow.Flow

interface RepositoryRecipes {
    fun getPopular(): Flow<PagingData<RecipeShort>>

    fun getByFilterShort(filter: SearchFilter): Flow<PagingData<RecipeShort>>

    fun getByIdExtended(id: String): Flow<RecipeExtended>
}