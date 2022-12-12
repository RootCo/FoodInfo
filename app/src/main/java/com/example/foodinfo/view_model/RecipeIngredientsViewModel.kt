package com.example.foodinfo.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodinfo.repository.RepositoryRecipes
import com.example.foodinfo.repository.model.RecipeIngredientModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject


class RecipeIngredientsViewModel @Inject constructor(repositoryRecipes: RepositoryRecipes) :
    ViewModel() {

    var recipeId: String = ""

    val ingredients: SharedFlow<List<RecipeIngredientModel>> by lazy {
        repositoryRecipes.getByIdIngredients(recipeId).map { it.data }.flowOn(Dispatchers.IO)
            .shareIn(viewModelScope, SharingStarted.WhileSubscribed())
    }
}