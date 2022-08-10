package com.example.foodinfo.repository

import androidx.paging.PagingData
import com.example.foodinfo.local.model.RecipeExtendedModel
import com.example.foodinfo.local.model.RecipeShortModel
import com.example.foodinfo.local.model.SearchFilterModel
import kotlinx.coroutines.flow.Flow

interface RepositoryRecipes {
    fun getPopular(): Flow<PagingData<RecipeShortModel>>

    fun getByFilterShort(filter: SearchFilterModel): Flow<PagingData<RecipeShortModel>>

    fun getByIdExtended(id: String): Flow<RecipeExtendedModel>
}