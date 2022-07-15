package com.example.foodinfo.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodinfo.model.local.RecipeCategoryLabelItem
import com.example.foodinfo.model.local.dao.filter_field.CategoryField
import com.example.foodinfo.model.local.entities.SearchFilter
import com.example.foodinfo.model.repository.RepositoryRecipes
import javax.inject.Inject

class ExploreViewModel @Inject constructor(
    private val repositoryRecipes: RepositoryRecipes
) : ViewModel() {

    private val _recipes: MutableLiveData<Map<String, List<RecipeCategoryLabelItem>>> by lazy {
        MutableLiveData<Map<String, List<RecipeCategoryLabelItem>>>()
    }
    val recipes: LiveData<Map<String, List<RecipeCategoryLabelItem>>>
        get() = _recipes

    val categories: Array<CategoryField.Fields>
        get() = CategoryField.Fields.values()

    fun updateRecipes() {
        val data: MutableMap<String, List<RecipeCategoryLabelItem>> = mutableMapOf()
        for (category in CategoryField.Fields.values()) {
            val categoryItem = arrayListOf<RecipeCategoryLabelItem>()
            for (label in category.validLabels) {
                val searchFilter = SearchFilter()
                searchFilter.setSelector(SearchFilter.RECIPE_SELECTOR_SHORT)
                searchFilter.categoryFields.add(CategoryField(category, listOf(label)))
                searchFilter.buildQuery()
                categoryItem.add(
                    RecipeCategoryLabelItem(
                        label, repositoryRecipes.getByFilterShort(searchFilter)
                    )
                )
            }
            data[category.label] = categoryItem
        }
        _recipes.value = data
    }
}
