package com.example.foodinfo.view_model

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.foodinfo.model.local.RecipeCategoryLabelItem
import com.example.foodinfo.model.local.dao.filter_field.CategoryField
import com.example.foodinfo.model.local.entities.SearchFilter
import com.example.foodinfo.model.repository.RepositoryRecipes

class ExploreCategoriesDataSource(
    private val repositoryRecipes: RepositoryRecipes,
    private val category: String,
    private var labels: ArrayList<String>
) : PagingSource<Int, RecipeCategoryLabelItem>() {

    override fun getRefreshKey(state: PagingState<Int, RecipeCategoryLabelItem>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RecipeCategoryLabelItem> {
        val currentPosition = params.key ?: INITIAL_POSITION
        val loadSize = params.loadSize.coerceAtMost(labels.size - 1)

        var nextPosition = currentPosition + 1
        val prevPosition =
            if (currentPosition != 0) currentPosition - 1 else INITIAL_POSITION

        val data = arrayListOf<RecipeCategoryLabelItem>()

        for (index in currentPosition..loadSize) {
            nextPosition += 1
            val filter = SearchFilter()
            filter.categoryFields.add(
                CategoryField(
                    CategoryField.fromLabel(category),
                    listOf(labels[index])
                )
            )
            filter.buildQuery()
            data.add(
                RecipeCategoryLabelItem(
                    category,
                    labels[index],
                    repositoryRecipes.getByFilterExplore(filter)
                )
            )
        }
        nextPosition += 1
        val prev = if (prevPosition == 0) null else prevPosition
        val next = if (nextPosition > labels.size) null else nextPosition

        return LoadResult.Page(data, prev, next)
    }

    companion object {
        const val INITIAL_POSITION = 0
    }
}