package com.example.foodinfo.model.repository.impl

import com.example.foodinfo.model.local.dao.FoodDAO
import com.example.foodinfo.model.local.Food
import com.example.foodinfo.model.repository.RepositoryFood
import com.example.foodinfo.utils.ResourcesProvider
import javax.inject.Inject


class RepositoryFoodImpl @Inject constructor(
    private val resourcesProvider: ResourcesProvider, private val foodDAO: FoodDAO
) : RepositoryFood {

    override fun getDaily(): Food {
        return loadPreview(foodDAO.getDaily())
    }

    private fun loadPreview(recipe: Food): Food {
        recipe.preview = resourcesProvider.getDrawableByName(recipe.previewURL)
        return recipe
    }
}