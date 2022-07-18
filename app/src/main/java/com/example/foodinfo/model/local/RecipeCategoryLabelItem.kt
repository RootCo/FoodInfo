package com.example.foodinfo.model.local

import androidx.paging.PagingData
import androidx.recyclerview.widget.DiffUtil
import kotlinx.coroutines.flow.Flow


data class RecipeCategoryLabelItem(
    val category: String,
    val label: String,
    val recipes: Flow<PagingData<RecipeExplore>>
) {
    object ItemCallBack :
        DiffUtil.ItemCallback<RecipeCategoryLabelItem>() {

        override fun areItemsTheSame(
            oldItem: RecipeCategoryLabelItem, newItem: RecipeCategoryLabelItem
        ): Boolean {
            return oldItem.category == newItem.category &&
                    oldItem.label == newItem.label
        }

        override fun areContentsTheSame(
            oldItem: RecipeCategoryLabelItem, newItem: RecipeCategoryLabelItem
        ): Boolean {
            return oldItem.category == newItem.category &&
                    oldItem.label == newItem.label
        }
    }
}