package com.example.foodinfo.repository.impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.foodinfo.local.dao.RecipesDAO
import com.example.foodinfo.repository.RepositoryRecipes
import com.example.foodinfo.repository.mapper.toModel
import com.example.foodinfo.repository.mapper.toModelShort
import com.example.foodinfo.repository.model.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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

    override fun getByFilter(filter: SearchFilterModel): Flow<PagingData<RecipeShortModel>> {
        return Pager(
            config = DB_EXPLORE_PAGER,
            pagingSourceFactory = {
                recipesDAO.getByFilter(SimpleSQLiteQuery(filter.query))
            }
        ).flow.map { pagingData -> pagingData.map { it.toModelShort() } }
    }

    override fun getById(id: String): Flow<RecipeModel> {
        return recipesDAO.getById(id).map { it.toModel() }
    }

    override fun getByIdIngredients(id: String): Flow<List<RecipeIngredientModel>> {
        return recipesDAO.getByIdIngredients(id).map { list -> list.map { it.toModel() } }
    }

    override fun getByIdNutrients(id: String): Flow<List<RecipeNutrientModel>> {
        return recipesDAO.getByIdNutrients(id).map { list -> list.map { it.toModel() } }
    }

    override fun getByIdLabels(id: String): Flow<RecipeLabelsModel> {
        return recipesDAO.getByIdLabels(id).map { it.toModel() }
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
    }
}