package com.example.foodinfo.view_model

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.foodinfo.model.local.RecipeCategoryLabelItem
import com.example.foodinfo.model.local.dao.filter_field.CategoryField
import com.example.foodinfo.model.local.entities.SearchFilter
import com.example.foodinfo.model.repository.RepositoryRecipes
import com.example.foodinfo.model.repository.impl.RepositoryRecipesImpl


class ExploreCategoriesDataSource(
    private val repositoryRecipes: RepositoryRecipes,
    private val category: String
) : PagingSource<Int, RecipeCategoryLabelItem>() {

    private val pages: List<List<Pair<String, SearchFilter>>>

    init {
        pages = CategoryField.fromLabel(category).validLabels.map { label ->
            Pair(label, createFilter(label))
        }.chunked(RepositoryRecipesImpl.DB_EXPLORE_OUTER_PAGER.pageSize)
    }

    private fun createFilter(label: String): SearchFilter {
        val filter = SearchFilter()
        filter.categoryFields.add(
            CategoryField(
                CategoryField.fromLabel(category),
                listOf(label)
            )
        )
        filter.buildQuery()
        return filter
    }


    override fun getRefreshKey(state: PagingState<Int, RecipeCategoryLabelItem>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RecipeCategoryLabelItem> {
        val targetPosition = params.key ?: 0
        val prevPosition = if (targetPosition > 0) targetPosition - 1 else null
        val nextPosition =
            if (targetPosition < pages.lastIndex) targetPosition + 1 else null

        val data = pages[targetPosition].map { pair ->
            RecipeCategoryLabelItem(
                category,
                pair.first,
                repositoryRecipes.getByFilterExplore(pair.second)
            )
        }

        return LoadResult.Page(data, prevPosition, nextPosition)
    }
}