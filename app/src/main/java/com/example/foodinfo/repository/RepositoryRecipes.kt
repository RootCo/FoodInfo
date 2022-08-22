package com.example.foodinfo.repository

import androidx.paging.PagingData
import com.example.foodinfo.repository.model.*
import kotlinx.coroutines.flow.Flow

interface RepositoryRecipes {
    fun getPopular(): Flow<PagingData<RecipeShortModel>>

    fun getFavorite(): Flow<PagingData<RecipeShortModel>>

    fun getByFilter(filter: SearchFilterModel): Flow<PagingData<RecipeShortModel>>

    fun getById(id: String): Flow<RecipeModel>

    fun getByIdIngredients(id: String): Flow<List<RecipeIngredientModel>>

    fun getByIdNutrients(id: String): Flow<List<RecipeNutrientModel>>

    fun getByIdLabels(id: String): Flow<RecipeLabelsModel>

    fun updateFavoriteMark(id: String)
}