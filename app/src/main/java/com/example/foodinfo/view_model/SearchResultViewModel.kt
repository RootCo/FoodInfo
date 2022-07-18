package com.example.foodinfo.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodinfo.model.local.RecipeExplore
import com.example.foodinfo.model.local.entities.SearchFilter
import com.example.foodinfo.model.repository.RepositoryRecipes
import com.example.foodinfo.model.repository.RepositorySearchFilter
import javax.inject.Inject

class SearchResultViewModel @Inject constructor(
    private val repositoryRecipes: RepositoryRecipes,
    private val repositorySearchFilter: RepositorySearchFilter
) : ViewModel() {

    private val _recipes: MutableLiveData<List<RecipeExplore>> by lazy {
        MutableLiveData<List<RecipeExplore>>()
    }
    val recipes: LiveData<List<RecipeExplore>>
        get() = _recipes

    fun updateRecipes(header: String) {
        val filter = SearchFilter()
        filter.inputText = header
        filter.buildQuery()
//        _recipes.value = repositoryRecipes.getByFilterExplore(filter)
    }
}