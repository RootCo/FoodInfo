package com.example.foodinfo.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodinfo.repository.RepositoryRecipes
import com.example.foodinfo.repository.model.RecipeIngredientModel
import com.example.foodinfo.repository.model.RecipeLabelsModel
import com.example.foodinfo.repository.model.RecipeModel
import com.example.foodinfo.repository.model.RecipeNutrientModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject


class RecipeExtendedViewModel @Inject constructor(repositoryRecipes: RepositoryRecipes) :
    ViewModel() {

    var recipeId: String = ""

    val recipe: SharedFlow<RecipeModel> by lazy {
        repositoryRecipes.getById(recipeId).flowOn(Dispatchers.IO)
            .shareIn(viewModelScope, SharingStarted.WhileSubscribed(), 1)
    }

    val ingredients: SharedFlow<List<RecipeIngredientModel>> by lazy {
        repositoryRecipes.getByIdIngredients(recipeId).flowOn(Dispatchers.IO)
            .shareIn(viewModelScope, SharingStarted.WhileSubscribed(), 1)
    }

    val nutrients: SharedFlow<List<RecipeNutrientModel>> by lazy {
        repositoryRecipes.getByIdNutrients(recipeId).flowOn(Dispatchers.IO)
            .shareIn(viewModelScope, SharingStarted.WhileSubscribed(), 1)
    }

    val labels: SharedFlow<RecipeLabelsModel> by lazy {
        repositoryRecipes.getByIdLabels(recipeId).flowOn(Dispatchers.IO)
            .shareIn(viewModelScope, SharingStarted.WhileSubscribed(), 1)
    }
}