package com.example.foodinfo.model.repository.impl

import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.foodinfo.model.local.RecipeFull
import com.example.foodinfo.model.local.RecipeShort
import com.example.foodinfo.model.local.dao.RecipesDAO
import com.example.foodinfo.model.local.entities.SearchFilter
import com.example.foodinfo.model.repository.RepositoryRecipes
import com.example.foodinfo.utils.ResourcesProvider
import javax.inject.Inject


class RepositoryRecipesImpl @Inject constructor(
    private val resourcesProvider: ResourcesProvider, private val recipesDAO: RecipesDAO
) : RepositoryRecipes {

    override fun getDaily(): List<RecipeShort> {
        return recipesDAO.getDaily().map { recipe ->
            recipe.preview = resourcesProvider.getDrawableByName(recipe.previewURL)
            recipe
        }
    }

    override fun getByFilterShort(filter: SearchFilter): List<RecipeShort> {
        return recipesDAO.getByFilterShort(SimpleSQLiteQuery(filter.query))
            .map { recipe ->
                recipe.preview = resourcesProvider.getDrawableByName(recipe.previewURL)
                recipe
            }
    }

    override fun getByFilterFull(filter: SearchFilter): List<RecipeFull> {
        return recipesDAO.getByFilterFull(SimpleSQLiteQuery(filter.query))
            .map { recipe ->
                recipe.preview = resourcesProvider.getDrawableByName(recipe.previewURL)
                recipe
            }
    }

    override fun getById(id: String): RecipeShort {
        val recipe = recipesDAO.getById(id)
        recipe.preview = resourcesProvider.getDrawableByName(recipe.previewURL)
        return recipe
    }
}