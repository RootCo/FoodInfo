package com.example.foodinfo.view_model

import androidx.lifecycle.ViewModel
import com.example.foodinfo.repository.RepositoryLabels
import com.example.foodinfo.repository.model.LabelModel
import javax.inject.Inject


class SearchTargetViewModel @Inject constructor(
    private val repositoryLabels: RepositoryLabels
) : ViewModel() {
    val featureName = "Search recipes"

    fun getLabel(category: String, label: String): LabelModel {
        return repositoryLabels.getByLabel(category, label)
    }
}