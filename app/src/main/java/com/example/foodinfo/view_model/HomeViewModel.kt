package com.example.foodinfo.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodinfo.model.local.RecipeExplore
import com.example.foodinfo.model.repository.RepositoryRecipes
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val repositoryRecipes: RepositoryRecipes,
) : ViewModel() {

    private val _dailyRecipe: MutableLiveData<RecipeExplore> by lazy {
        MutableLiveData<RecipeExplore>()
    }
    val dailyRecipe: LiveData<RecipeExplore>
        get() = _dailyRecipe

    private val _recipes: MutableLiveData<List<RecipeExplore>> by lazy {
        MutableLiveData<List<RecipeExplore>>()
    }
    val recipes: LiveData<List<RecipeExplore>>
        get() = _recipes


    fun updateRecipes() {
        _recipes.value = repositoryRecipes.getPopular()
    }

    fun updateFood() {
        _dailyRecipe.value = repositoryRecipes.getDaily()
    }
}