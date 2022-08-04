package com.example.foodinfo.view_model

import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.foodinfo.model.local.CategoryItem
import com.example.foodinfo.model.local.RecipeExplore
import com.example.foodinfo.model.local.dao.filter_field.CategoryField
import com.example.foodinfo.model.local.entities.SearchFilter
import com.example.foodinfo.model.repository.impl.RepositoryRecipesImpl
import kotlinx.coroutines.flow.StateFlow


class ExploreCategoriesDataSource(
    private val getRecipes: (SearchFilter) -> StateFlow<PagingData<RecipeExplore>>,
    private val category: String
) : PagingSource<Int, CategoryItem>() {

    private val pages: List<List<Pair<String, SearchFilter>>>

    init {
        pages = CategoryField.fromLabel(category).labels.map { label ->
            Pair(label, createFilter(label))
        }.chunked(RepositoryRecipesImpl.DB_EXPLORE_OUTER_PAGER.pageSize)
    }

    private fun createFilter(label: String): SearchFilter {
        val filter = SearchFilter()
        filter.categoryFields.add(
            CategoryField(CategoryField.fromLabel(category), listOf(label))
        )
        filter.buildQuery()
        return filter
    }


    override fun getRefreshKey(state: PagingState<Int, CategoryItem>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CategoryItem> {
        val targetPosition = params.key ?: 0

        val data = pages[targetPosition].map { pair ->
            CategoryItem(category, pair.first, getRecipes(pair.second))
        }

        return LoadResult.Page(
            data,
            if (targetPosition > 0) targetPosition - 1 else null,
            if (targetPosition < pages.lastIndex) targetPosition + 1 else null
        )
    }
}