package com.example.foodinfo.model.dao.impl

import com.example.foodinfo.model.dao.RecipesDAO
import com.example.foodinfo.model.entities.RecipeCategoryLabels
import com.example.foodinfo.model.entities.RecipeShort
import com.example.foodinfo.model.entities.SearchFilter
import com.example.foodinfo.utils.AssetProvider
import com.example.foodinfo.utils.AssetsKeyWords
import com.example.foodinfo.utils.JSONLoader
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import javax.inject.Inject


class RecipesDAOImpl @Inject constructor(assetProvider: AssetProvider) : RecipesDAO {

    private var recipes: ArrayList<RecipeShort>
    private var recipesCategories: ArrayList<RecipeCategoryLabels>

    init {
        val jsonLoader = JSONLoader()
        val gson = GsonBuilder().create()

        val recipesString =
            jsonLoader.load(assetProvider.getAsset(AssetsKeyWords.RECIPES_ASSET))
                .get(AssetsKeyWords.SHORT).toString()
        val typeRecipes = object : TypeToken<List<RecipeShort>>() {}.type
        recipes = gson.fromJson(recipesString, typeRecipes)

        val categoriesString =
            jsonLoader.load(assetProvider.getAsset(AssetsKeyWords.RECIPES_CATEGORIES_ASSET))
                .get(AssetsKeyWords.CATEGORIES).toString()
        val typeCategories = object : TypeToken<List<RecipeCategoryLabels>>() {}.type
        recipesCategories = gson.fromJson(categoriesString, typeCategories)
    }

    /*
    перемешиваем для иммититации того, что якобы с сервера вытянули
    данные, чтобы они отличались друг от друга
    копию делаем т.к. в будущем каждый массив recipes будет новым (взят из базы)
    и не будет хранится в этом классе в виде переменной recipes
     */
    override fun getDaily(): List<RecipeShort> {
        val recipes = recipes.mapTo(arrayListOf()) { it.copy() }
        recipes.shuffle()
        return recipes
    }

    override fun getByCategory(category: String, label: String): List<RecipeShort> {
        val recipes = recipes.mapTo(arrayListOf()) { it.copy() }
        recipes.shuffle()
        return recipes
    }

    override fun getByFilter(
        query: String, searchFilter: SearchFilter
    ): List<RecipeShort> {
        val recipes = recipes.mapTo(arrayListOf()) { it.copy() }
        recipes.shuffle()
        return recipes
    }

    override fun getById(id: String): RecipeShort {
        return recipes.filter { field -> field.id == id }.single()
    }

    override fun getRecipeCategories(): List<RecipeCategoryLabels> {
        return recipesCategories.mapTo(arrayListOf()) { it.copy() }
    }
}