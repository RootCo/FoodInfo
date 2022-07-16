package com.example.foodinfo.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodinfo.model.local.RecipeExtended
import com.example.foodinfo.model.repository.RepositoryRecipes
import javax.inject.Inject

class RecipeExtendedViewModel @Inject constructor(
    private val repositoryRecipes: RepositoryRecipes
) : ViewModel() {

    private val _recipe: MutableLiveData<RecipeExtended> by lazy {
        MutableLiveData<RecipeExtended>()
    }
    val recipe: LiveData<RecipeExtended>
        get() = _recipe

    fun loadRecipe(recipeId: String) {
        _recipe.value = repositoryRecipes.getById(recipeId)
    }
}