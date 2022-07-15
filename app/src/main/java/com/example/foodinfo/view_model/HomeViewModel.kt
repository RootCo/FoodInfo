package com.example.foodinfo.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodinfo.model.local.Food
import com.example.foodinfo.model.local.RecipeShort
import com.example.foodinfo.model.repository.RepositoryFood
import com.example.foodinfo.model.repository.RepositoryRecipes
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val repositoryRecipes: RepositoryRecipes,
    private val repositoryFood: RepositoryFood
) : ViewModel() {

    private val _food: MutableLiveData<Food> by lazy {
        MutableLiveData<Food>()
    }
    val food: LiveData<Food>
        get() = _food

    private val _recipes: MutableLiveData<List<RecipeShort>> by lazy {
        MutableLiveData<List<RecipeShort>>()
    }
    val recipes: LiveData<List<RecipeShort>>
        get() = _recipes


    fun updateRecipes() {
        _recipes.value = repositoryRecipes.getDaily()
    }

    fun updateFood() {
        _food.value = repositoryFood.getDaily()
    }
}