package com.example.foodinfo.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodinfo.model.entities.RecipeCategoryLabelItem
import com.example.foodinfo.model.entities.RecipeCategoryLabels
import com.example.foodinfo.model.repository.RepositoryRecipes
import javax.inject.Inject

class ExploreViewModel @Inject constructor(private val repositoryRecipes: RepositoryRecipes) :
    ViewModel() {

    private val _recipes: MutableLiveData<Map<String, List<RecipeCategoryLabelItem>>> by lazy {
        MutableLiveData<Map<String, List<RecipeCategoryLabelItem>>>()
    }
    val recipes: LiveData<Map<String, List<RecipeCategoryLabelItem>>>
        get() = _recipes

    val categories: List<RecipeCategoryLabels>
        get() = repositoryRecipes.getCategories()

    fun updateRecipes() {
        val data: MutableMap<String, List<RecipeCategoryLabelItem>> = mutableMapOf()
        for (category in repositoryRecipes.getCategories()) {
            val categoryItem = arrayListOf<RecipeCategoryLabelItem>()
            for (label in category.labels) {
                categoryItem.add(
                    RecipeCategoryLabelItem(
                        label, repositoryRecipes.getByCategory(category.name, label)
                    )
                )
            }
            data[category.name] = categoryItem
        }
        _recipes.value = data
    }
}