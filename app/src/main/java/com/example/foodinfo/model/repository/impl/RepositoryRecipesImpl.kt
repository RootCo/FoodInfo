package com.example.foodinfo.model.repository.impl

import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.foodinfo.model.local.RecipeExplore
import com.example.foodinfo.model.local.RecipeExtended
import com.example.foodinfo.model.local.RecipeResult
import com.example.foodinfo.model.local.dao.RecipesDAO
import com.example.foodinfo.model.local.entities.SearchFilter
import com.example.foodinfo.model.repository.RepositoryRecipes
import com.example.foodinfo.utils.ResourcesProvider
import javax.inject.Inject


class RepositoryRecipesImpl @Inject constructor(
    private val resourcesProvider: ResourcesProvider, private val recipesDAO: RecipesDAO
) : RepositoryRecipes {

    override fun getDaily(): RecipeExplore {
        val recipe = recipesDAO.getDaily()
        recipe.preview = resourcesProvider.getDrawableByName(recipe.previewURL)
        return recipe
    }

    override fun getPopular(): List<RecipeExplore> {
        return recipesDAO.getPopular().map { recipe ->
            recipe.preview = resourcesProvider.getDrawableByName(recipe.previewURL)
            recipe
        }
    }

    override fun getByFilterResult(filter: SearchFilter): List<RecipeResult> {
        return recipesDAO.getByFilterResult(SimpleSQLiteQuery(filter.query))
            .map { recipe ->
                recipe.preview = resourcesProvider.getDrawableByName(recipe.previewURL)
                recipe
            }
    }

    override fun getByFilterExplore(filter: SearchFilter): List<RecipeExplore> {
        return recipesDAO.getByFilterExplore(SimpleSQLiteQuery(filter.query))
            .map { recipe ->
                recipe.preview = resourcesProvider.getDrawableByName(recipe.previewURL)
                recipe
            }
    }

    override fun getByFilterExtended(filter: SearchFilter): List<RecipeExtended> {
        return recipesDAO.getByFilterExtended(SimpleSQLiteQuery(filter.query))
            .map { recipe ->
                recipe.preview = resourcesProvider.getDrawableByName(recipe.previewURL)
                recipe
            }
    }

    override fun getById(id: String): RecipeExtended {
        val recipe = recipesDAO.getById(id)
        recipe.preview = resourcesProvider.getDrawableByName(recipe.previewURL)
        return recipe
    }
}