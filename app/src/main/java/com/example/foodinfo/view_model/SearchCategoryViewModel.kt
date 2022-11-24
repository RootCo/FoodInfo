package com.example.foodinfo.view_model

import androidx.lifecycle.ViewModel
import com.example.foodinfo.repository.RepositoryLabels
import com.example.foodinfo.repository.model.CategoryModel
import com.example.foodinfo.repository.model.LabelModel
import javax.inject.Inject


class SearchCategoryViewModel @Inject constructor(
    private val repositoryLabels: RepositoryLabels
) : ViewModel() {

    lateinit var categoryName: String

    val category: CategoryModel by lazy {
        repositoryLabels.getCategory(categoryName)
    }

    val labels: List<LabelModel> by lazy {
        repositoryLabels.getLabels(categoryName)
    }
}