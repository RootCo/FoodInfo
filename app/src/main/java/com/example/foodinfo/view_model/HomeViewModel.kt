package com.example.foodinfo.view_model

import androidx.lifecycle.ViewModel
import com.example.foodinfo.repository.RepositoryLabels
import com.example.foodinfo.repository.RepositoryRecipes
import com.example.foodinfo.repository.model.CategoryModel
import javax.inject.Inject


class HomeViewModel @Inject constructor(
    private val repositoryRecipes: RepositoryRecipes,
    repositoryLabels: RepositoryLabels
) : ViewModel() {

    val categories: List<CategoryModel> by lazy {
        repositoryLabels.getCategories()
    }
}