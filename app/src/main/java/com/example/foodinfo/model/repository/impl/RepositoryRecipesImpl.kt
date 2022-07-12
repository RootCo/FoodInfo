package com.example.foodinfo.model.repository.impl

import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.foodinfo.model.dao.RecipesDAO
import com.example.foodinfo.model.entities.RecipeCategoryLabels
import com.example.foodinfo.model.entities.RecipeShort
import com.example.foodinfo.model.entities.SearchFilter
import com.example.foodinfo.model.repository.RepositoryRecipes
import com.example.foodinfo.utils.ResourcesProvider
import javax.inject.Inject

class RepositoryRecipesImpl @Inject constructor(
    private val resourcesProvider: ResourcesProvider, private val recipesDAO: RecipesDAO
) : RepositoryRecipes {

    override fun getDaily(): List<RecipeShort> {
        return recipesDAO.getDaily().map { loadPreview(it) } as ArrayList
    }

    override fun getByFilter(filter: SearchFilter): List<RecipeShort> {

        // TODO формирование query запроса на из SearchFilter
        return recipesDAO.getByFilter(SimpleSQLiteQuery(""))
            .map { loadPreview(it) } as ArrayList
    }

    override fun getById(id: String): RecipeShort {
        return loadPreview(recipesDAO.getById(id))
    }

    override fun getCategories(): List<RecipeCategoryLabels> {
        return recipesDAO.getRecipeCategories()
    }

    private fun loadPreview(recipe: RecipeShort): RecipeShort {
        recipe.preview = resourcesProvider.getDrawableByName(recipe.previewURL)
        return recipe
    }
}