package com.example.foodinfo.repository.impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.foodinfo.local.dao.RecipesDAO
import com.example.foodinfo.repository.model.RecipeExtendedModel
import com.example.foodinfo.repository.model.RecipeShortModel
import com.example.foodinfo.repository.model.SearchFilterModel
import com.example.foodinfo.repository.RepositoryRecipes
import com.example.foodinfo.repository.mapper.toModel
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
        ).flow.map { pagingData -> pagingData.map { it.toModel() } }
    }

    override fun getByFilterShort(filter: SearchFilterModel): Flow<PagingData<RecipeShortModel>> {
        return Pager(
            config = DB_EXPLORE_PAGER,
            pagingSourceFactory = {
                recipesDAO.getByFilterShort(SimpleSQLiteQuery(filter.query))
            }
        ).flow.map { pagingData -> pagingData.map { it.toModel() } }
    }

    override fun getByIdExtended(id: String): Flow<RecipeExtendedModel> {
        return recipesDAO.getByIdExtended(id).map { it.toModel() }
    }


    companion object {
        val DB_POPULAR_PAGER = PagingConfig(
            pageSize = 10,
            initialLoadSize = 20,
            jumpThreshold = 40,
            maxSize = 40
        )
        val DB_EXPLORE_PAGER = PagingConfig(
            pageSize = 5,
            initialLoadSize = 5,
            jumpThreshold = 15,
            maxSize = 15
        )
    }
}