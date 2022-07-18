package com.example.foodinfo.view_model

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingData
import com.example.foodinfo.model.local.RecipeCategoryLabelItem
import com.example.foodinfo.model.local.dao.filter_field.CategoryField
import com.example.foodinfo.model.repository.RepositoryRecipes
import com.example.foodinfo.model.repository.impl.RepositoryRecipesImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class ExploreViewModel @Inject constructor(
    private val repositoryRecipes: RepositoryRecipes
) : ViewModel() {

    fun getCategories(): List<String> {
        return CategoryField.Fields.values().map { it.label }
    }

    fun getRecipes(category: String): Flow<PagingData<RecipeCategoryLabelItem>> {
        return Pager(
            config = RepositoryRecipesImpl.DB_EXPLORE_OUTER_PAGER,
            pagingSourceFactory = {
                ExploreCategoriesDataSource(
                    repositoryRecipes,
                    category,
                    CategoryField.fromLabel(category).validLabels
                )
            }).flow
    }
}