package com.example.foodinfo.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodinfo.repository.RepositoryRecipeFieldsInfo
import com.example.foodinfo.repository.RepositoryRecipes
import com.example.foodinfo.repository.model.NutrientHintModel
import com.example.foodinfo.repository.model.NutrientRecipeModel
import com.example.foodinfo.utils.State
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject


class RecipeNutrientsViewModel @Inject constructor(
    repositoryRecipes: RepositoryRecipes,
    private val repositoryRecipeFieldsInfo: RepositoryRecipeFieldsInfo
) :
    ViewModel() {

    var recipeId: String = ""

    val nutrients: SharedFlow<State<List<NutrientRecipeModel>>> by lazy {
        repositoryRecipes.getByIdNutrients(recipeId).shareIn(
            viewModelScope, SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000), 1
        )
    }

    fun getNutrient(label: String): NutrientHintModel {
        return repositoryRecipeFieldsInfo.getNutrientHint(label)
    }
}