package com.example.foodinfo.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodinfo.model.local.RecipeExplore
import com.example.foodinfo.model.local.dao.filter_field.CategoryField
import com.example.foodinfo.model.local.entities.SearchFilter
import com.example.foodinfo.model.repository.RepositoryRecipes
import javax.inject.Inject

class SearchTargetViewModel @Inject constructor(
    private val repositoryRecipes: RepositoryRecipes
) : ViewModel() {

    private val _recipes: MutableLiveData<List<RecipeExplore>> by lazy {
        MutableLiveData<List<RecipeExplore>>()
    }
    val recipes: LiveData<List<RecipeExplore>>
        get() = _recipes

    fun updateRecipes(category: String, label: String) {
        val filter = SearchFilter()
        filter.categoryFields.add(
            CategoryField(
                CategoryField.fromLabel(category),
                listOf(label)
            )
        )
        filter.buildQuery()
        _recipes.value = repositoryRecipes.getByFilterExplore(filter)
    }
}