package com.example.foodinfo.repository

import androidx.paging.PagingData
import com.example.foodinfo.repository.model.*
import com.example.foodinfo.utils.State
import kotlinx.coroutines.flow.Flow


interface RepositoryRecipes {
    fun getPopular(): Flow<PagingData<RecipeShortModel>>

    fun getFavorite(): Flow<PagingData<RecipeFavoriteModel>>

    fun getFavoriteIds(): List<String>

    fun getByFilter(filter: SearchFilterModel): Flow<PagingData<RecipeShortModel>>

    fun getById(id: String): Flow<State<RecipeModel>>

    fun getByIdIngredients(id: String): Flow<State<List<RecipeIngredientModel>>>

    fun getByIdNutrients(id: String): Flow<State<List<NutrientRecipeModel>>>

    fun getByIdLabels(id: String): Flow<State<List<CategoryRecipeModel>>>

    fun updateFavoriteMark(id: String)

    fun delFromFavorite(id: List<String>)
}