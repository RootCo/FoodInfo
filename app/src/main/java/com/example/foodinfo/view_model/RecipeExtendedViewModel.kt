package com.example.foodinfo.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodinfo.repository.RepositoryLabels
import com.example.foodinfo.repository.RepositoryRecipes
import com.example.foodinfo.repository.model.*
import com.example.foodinfo.utils.State
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject


class RecipeExtendedViewModel @Inject constructor(
    private val repositoryRecipes: RepositoryRecipes,
    private val repositoryLabels: RepositoryLabels
) :
    ViewModel() {

    var recipeId: String = ""

    val recipe: SharedFlow<State<RecipeModel>> by lazy {
        repositoryRecipes.getById(recipeId).shareIn(
            viewModelScope, SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000), 1
        )
    }

    val ingredients: SharedFlow<State<List<RecipeIngredientModel>>> by lazy {
        repositoryRecipes.getByIdIngredients(recipeId).shareIn(
            viewModelScope, SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000), 1
        )
    }

    val nutrients: SharedFlow<State<List<RecipeNutrientModel>>> by lazy {
        repositoryRecipes.getByIdNutrients(recipeId).shareIn(
            viewModelScope, SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000), 1
        )
    }

    val labels: SharedFlow<State<List<CategoryLabelsModel>>> by lazy {
        repositoryRecipes.getByIdLabels(recipeId).shareIn(
            viewModelScope, SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000), 1
        )
    }


    fun getLabel(category: String, label: String): LabelModel {
        return repositoryLabels.getLabel(category, label)
    }

    fun updateFavoriteMark() {
        repositoryRecipes.updateFavoriteMark(recipeId)
    }
}