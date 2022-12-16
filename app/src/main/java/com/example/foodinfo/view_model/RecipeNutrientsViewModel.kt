package com.example.foodinfo.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodinfo.repository.RepositoryNutrients
import com.example.foodinfo.repository.RepositoryRecipes
import com.example.foodinfo.repository.model.NutrientModel
import com.example.foodinfo.repository.model.NutrientRecipeModel
import com.example.foodinfo.utils.State
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject


class RecipeNutrientsViewModel @Inject constructor(
    repositoryRecipes: RepositoryRecipes,
    private val repositoryNutrients: RepositoryNutrients
) :
    ViewModel() {

    var recipeId: String = ""

    val nutrients: SharedFlow<State<List<NutrientRecipeModel>>> by lazy {
        repositoryRecipes.getByIdNutrients(recipeId).shareIn(
            viewModelScope, SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000), 1
        )
    }

    fun getNutrient(label: String): NutrientModel {
        return repositoryNutrients.getByLabel(label)
    }
}