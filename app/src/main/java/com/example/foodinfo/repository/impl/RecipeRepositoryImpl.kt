package com.example.foodinfo.repository.impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.foodinfo.local.dao.RecipeDAO
import com.example.foodinfo.repository.RecipeRepository
import com.example.foodinfo.repository.mapper.*
import com.example.foodinfo.repository.model.*
import com.example.foodinfo.utils.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class RecipeRepositoryImpl @Inject constructor(
    private val recipeDAO: RecipeDAO
) : RecipeRepository {

    override fun getPopular(): Flow<PagingData<RecipeShortModel>> {
        return Pager(
            config = DB_POPULAR_PAGER,
            pagingSourceFactory = {
                recipeDAO.getPopular()
            }
        ).flow.map { pagingData -> pagingData.map { it.toModelShort() } }.flowOn(Dispatchers.IO)
    }

    override fun getFavorite(): Flow<PagingData<RecipeFavoriteModel>> {
        return Pager(
            config = DB_FAVORITE_PAGER,
            pagingSourceFactory = {
                recipeDAO.getFavorite()
            }
        ).flow.map { pagingData -> pagingData.map { it.toModelFavorite() } }.flowOn(Dispatchers.IO)
    }

    override fun getFavoriteIds(): List<String> {
        return recipeDAO.getFavoriteIds()
    }

    override fun getByFilter(query: String): Flow<PagingData<RecipeShortModel>> {
        return Pager(
            config = DB_EXPLORE_PAGER,
            pagingSourceFactory = {
                recipeDAO.getByFilter(SimpleSQLiteQuery(query))
            }
        ).flow.map { pagingData -> pagingData.map { it.toModelShort() } }.flowOn(Dispatchers.IO)
    }

    override fun getByIdExtended(recipeID: String): Flow<State<RecipeExtendedModel>> {
        return flow {
            emit(State.Loading())
            recipeDAO.getByIdExtended(recipeID).collect {
                emit(State.Success(it.toModelExtended()))
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun getByIdIngredients(recipeID: String): Flow<State<List<RecipeIngredientModel>>> {
        return flow {
            emit(State.Loading())
            recipeDAO.getIngredients(recipeID).collect { list ->
                emit(State.Success(list.map { it.toModel() }))
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun getByIdNutrients(recipeID: String): Flow<State<List<NutrientOfRecipeModel>>> {
        return flow {
            emit(State.Loading())
            recipeDAO.getNutrients(recipeID).collect { list ->
                emit(State.Success(list.map { it.toModel() }))
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun getByIdLabels(recipeID: String): Flow<State<List<CategoryOfRecipeModel>>> {
        return flow {
            emit(State.Loading())
            recipeDAO.getLabels(recipeID).collect {
                emit(State.Success(it.toModelRecipe()))
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun invertFavoriteStatus(ID: String) {
        recipeDAO.invertFavoriteStatus(ID)
    }

    override fun delFromFavorite(ID: List<String>) {
        recipeDAO.delFromFavorite(ID)
    }


    // definitely this is the wrong place to store pager configs but dunno where else
    companion object {
        val DB_POPULAR_PAGER = PagingConfig(
            pageSize = 10,
            initialLoadSize = 20,
            jumpThreshold = 40,
            maxSize = 40
        )
        val DB_EXPLORE_PAGER = PagingConfig(
            pageSize = 10,
            initialLoadSize = 20,
            jumpThreshold = 40,
            maxSize = 40
        )
        val DB_FAVORITE_PAGER = PagingConfig(
            pageSize = 10,
            initialLoadSize = 20,
            jumpThreshold = 40,
            maxSize = 40
        )
    }
}