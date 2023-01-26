package com.example.foodinfo.repository

import androidx.paging.PagingData
import com.example.foodinfo.repository.model.*
import com.example.foodinfo.utils.State
import kotlinx.coroutines.flow.Flow


interface RepositoryRecipes {
    fun getPopular(): Flow<PagingData<RecipeShortModel>>

    fun getFavorite(): Flow<PagingData<RecipeFavoriteModel>>

    fun getFavoriteIds(): List<String>

    fun getByFilter(query: String): Flow<PagingData<RecipeShortModel>>

    fun getByIdExtended(id: String): Flow<State<RecipeExtendedModel>>

    fun getByIdIngredients(id: String): Flow<State<List<RecipeIngredientModel>>>

    fun getByIdNutrients(id: String): Flow<State<List<NutrientRecipeModel>>>

    fun getByIdLabels(id: String): Flow<State<List<CategoryRecipeModel>>>

    fun invertFavoriteStatus(id: String)

    fun delFromFavorite(id: List<String>)
}