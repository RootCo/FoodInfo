package com.example.foodinfo.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodinfo.repository.RepositoryLabels
import com.example.foodinfo.repository.RepositoryRecipes
import com.example.foodinfo.repository.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject


class RecipeExtendedViewModel @Inject constructor(
    private val repositoryRecipes: RepositoryRecipes,
    private val repositoryLabels: RepositoryLabels
) :
    ViewModel() {

    var recipeId: String = ""

    val recipe: SharedFlow<RecipeModel> by lazy {
        repositoryRecipes.getById(recipeId).flowOn(Dispatchers.IO)
            .shareIn(viewModelScope, SharingStarted.WhileSubscribed())
    }

    val ingredients: SharedFlow<List<RecipeIngredientModel>> by lazy {
        repositoryRecipes.getByIdIngredients(recipeId).flowOn(Dispatchers.IO)
            .shareIn(viewModelScope, SharingStarted.WhileSubscribed())
    }

    val nutrients: SharedFlow<List<RecipeNutrientModel>> by lazy {
        repositoryRecipes.getByIdNutrients(recipeId).flowOn(Dispatchers.IO)
            .shareIn(viewModelScope, SharingStarted.WhileSubscribed())
    }

    val labels: SharedFlow<List<RecipeCategoryModel>> by lazy {
        repositoryRecipes.getByIdLabels(recipeId).flowOn(Dispatchers.IO)
            .shareIn(viewModelScope, SharingStarted.WhileSubscribed())
    }


    fun getLabel(category: String, label: String): LabelModel {
        return repositoryLabels.getLabel(category, label)
    }

    fun updateFavoriteMark() {
        repositoryRecipes.updateFavoriteMark(recipeId)
    }
}