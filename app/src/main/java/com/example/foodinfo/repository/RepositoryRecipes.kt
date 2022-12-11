package com.example.foodinfo.repository

import androidx.paging.PagingData
import com.example.foodinfo.repository.model.*
import com.example.foodinfo.utils.Resource
import kotlinx.coroutines.flow.Flow

interface RepositoryRecipes {
    fun getPopular(): Flow<PagingData<RecipeShortModel>>

    fun getFavorite(): Flow<PagingData<RecipeFavoriteModel>>

    fun getFavoriteIds(): List<String>

    fun getByFilter(filter: SearchFilterModel): Flow<PagingData<RecipeShortModel>>

    fun getById(id: String): Flow<Resource<RecipeModel>>

    fun getByIdIngredients(id: String): Flow<Resource<List<RecipeIngredientModel>>>

    fun getByIdNutrients(id: String): Flow<Resource<List<RecipeNutrientModel>>>

    fun getByIdLabels(id: String): Flow<Resource<RecipeLabelsModel>>

    fun updateFavoriteMark(id: String)

    fun delFromFavorite(id: List<String>)
}