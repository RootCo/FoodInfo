package com.example.foodinfo.view_model

import androidx.lifecycle.ViewModel
import com.example.foodinfo.model.local.CategoryItem
import com.example.foodinfo.model.local.LabelItem
import com.example.foodinfo.model.local.dao.filter_field.CategoryField
import com.example.foodinfo.utils.ResourcesProvider
import javax.inject.Inject


class ExploreViewModel @Inject constructor(
    private val resourcesProvider: ResourcesProvider
) : ViewModel() {

    val categories = initCategories()

    private fun initCategories(): List<CategoryItem> {
        return CategoryField.Fields.values().map { category ->
            val labels = category.labels.zip(category.icons) { label, icon ->
                LabelItem(
                    category.displayName,
                    label,
                    resourcesProvider.getDrawableByName(icon)!!
                )
            }
            CategoryItem(category.displayName, labels)
        }
    }
}