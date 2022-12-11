package com.example.foodinfo.repository.impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.foodinfo.local.dao.RecipesDAO
import com.example.foodinfo.repository.RepositoryRecipes
import com.example.foodinfo.repository.mapper.toModel
import com.example.foodinfo.repository.mapper.toModelFavorite
import com.example.foodinfo.repository.mapper.toModelShort
import com.example.foodinfo.repository.model.*
import com.example.foodinfo.utils.Resource
import kotlinx.coroutines.flow.*
import javax.inject.Inject


class RepositoryRecipesImpl @Inject constructor(
    private val recipesDAO: RecipesDAO
) : RepositoryRecipes {

    override fun getPopular(): Flow<PagingData<RecipeShortModel>> {
        return Pager(
            config = DB_POPULAR_PAGER,
            pagingSourceFactory = {
                recipesDAO.getPopular()
            }
        ).flow.map { pagingData -> pagingData.map { it.toModelShort() } }
    }

    override fun getFavorite(): Flow<PagingData<RecipeFavoriteModel>> {
        return Pager(
            config = DB_FAVORITE_PAGER,
            pagingSourceFactory = {
                recipesDAO.getFavorite()
            }
        ).flow.map { pagingData -> pagingData.map { it.toModelFavorite() } }
    }

    override fun getFavoriteIds(): List<String> {
        return recipesDAO.getFavoriteIds()
    }

    override fun getByFilter(filter: SearchFilterModel): Flow<PagingData<RecipeShortModel>> {
        return Pager(
            config = DB_EXPLORE_PAGER,
            pagingSourceFactory = {
                recipesDAO.getByFilter(SimpleSQLiteQuery(filter.query))
            }
        ).flow.map { pagingData -> pagingData.map { it.toModelShort() } }
    }

    // TODO: реализовать хэндл ошибок (если считаешь надо) 
    override fun getById(id: String): Flow<Resource<RecipeModel>> {
        return flow {
            emit(Resource.Loading(RecipeModel("","","",0,"",0,0,0,"",false)))
            recipesDAO.getById(id).collect() { emit(Resource.Success(it.toModel())) }
        }

    }

    override fun getByIdIngredients(id: String): Flow<Resource<List<RecipeIngredientModel>>> {
        return flow {
            emit(Resource.Loading(listOf(RecipeIngredientModel(0L,"","",0.0,0.0,"","","",""))))
            recipesDAO.getByIdIngredients(id).collect() {list -> emit(Resource.Success(list.map { it.toModel() })) }
        }
    }

    override fun getByIdNutrients(id: String): Flow<Resource<List<RecipeNutrientModel>>> {
        return flow {
            emit(Resource.Loading(listOf(RecipeNutrientModel(0L,"","",0.0,0.0,0))))
            recipesDAO.getByIdNutrients(id).collect() { list -> emit(Resource.Success(list.map { it.toModel() })) }
        }
    }

    override fun getByIdLabels(id: String): Flow<Resource<RecipeLabelsModel>> {
        return flow {
            emit(Resource.Loading(RecipeLabelsModel(mapOf(Pair("", listOf(""))))))
            recipesDAO.getByIdLabels(id).collect() { emit(Resource.Success(it.toModel())) }
        }
    }

    override fun updateFavoriteMark(id: String) {
        recipesDAO.updateFavoriteStatus(id)
    }

    override fun delFromFavorite(id: List<String>) {
        recipesDAO.delFromFavorite(id)
    }


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