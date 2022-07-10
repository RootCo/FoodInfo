package com.example.foodinfo.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodinfo.model.entities.RecipeShort
import com.example.foodinfo.model.repository.RepositoryRecipes
import javax.inject.Inject

class SearchTargetViewModel @Inject constructor(
    private val repositoryRecipes: RepositoryRecipes
) : ViewModel() {

    private val _recipes: MutableLiveData<List<RecipeShort>> by lazy {
        MutableLiveData<List<RecipeShort>>()
    }
    val recipes: LiveData<List<RecipeShort>>
        get() = _recipes

    fun updateRecipes(category: String, label: String) {
        _recipes.value = repositoryRecipes.getByCategory(category, label)
    }
}