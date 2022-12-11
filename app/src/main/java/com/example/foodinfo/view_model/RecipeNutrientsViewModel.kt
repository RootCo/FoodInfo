package com.example.foodinfo.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodinfo.repository.RepositoryNutrients
import com.example.foodinfo.repository.RepositoryRecipes
import com.example.foodinfo.repository.model.NutrientModel
import com.example.foodinfo.repository.model.RecipeNutrientModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject


class RecipeNutrientsViewModel @Inject constructor(
    repositoryRecipes: RepositoryRecipes,
    private val repositoryNutrients: RepositoryNutrients
) :
    ViewModel() {

    var recipeId: String = ""

    val nutrients: SharedFlow<List<RecipeNutrientModel>> by lazy {
        repositoryRecipes.getByIdNutrients(recipeId).map { it.data }.flowOn(Dispatchers.IO)
            .shareIn(viewModelScope, SharingStarted.WhileSubscribed())
    }

    fun getNutrient(label: String): NutrientModel {
        return repositoryNutrients.getByLabel(label)
    }
}