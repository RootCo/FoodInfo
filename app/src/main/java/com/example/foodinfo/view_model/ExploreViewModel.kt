package com.example.foodinfo.view_model

import android.os.Parcelable
import androidx.lifecycle.ViewModel
import com.example.foodinfo.model.local.Category
import com.example.foodinfo.model.repository.RepositoryLabels
import javax.inject.Inject


class ExploreViewModel @Inject constructor(
    private val repositoryLabels: RepositoryLabels
) : ViewModel() {

    val categories = initCategories()
    var scrollState: Parcelable? = null

    private fun initCategories(): List<Category> {
        return repositoryLabels.getAll().map {
            Category(it.key, it.value)
        }
    }
}