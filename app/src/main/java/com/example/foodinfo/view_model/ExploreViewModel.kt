package com.example.foodinfo.view_model

import android.os.Parcelable
import androidx.lifecycle.ViewModel
import com.example.foodinfo.model.local.CategoryItem
import com.example.foodinfo.model.repository.RepositoryCategoryLabels
import javax.inject.Inject


class ExploreViewModel @Inject constructor(
    private val repositoryCategoryLabels: RepositoryCategoryLabels
) : ViewModel() {

    val categories = initCategories()
    var scrollState: Parcelable? = null

    private fun initCategories(): List<CategoryItem> {
        return repositoryCategoryLabels.getAll().map {
            CategoryItem(it.key, it.value)
        }
    }
}