package com.example.foodinfo.model.local

import android.os.Parcelable
import androidx.paging.PagingData
import androidx.recyclerview.widget.DiffUtil
import kotlinx.coroutines.flow.StateFlow


data class CategoryItem(
    val category: String,
    val label: String,
    val recipes: StateFlow<PagingData<RecipeExplore>>
) {
    var state: Parcelable? = null

    object ItemCallBack :
        DiffUtil.ItemCallback<CategoryItem>() {

        override fun areItemsTheSame(
            oldItem: CategoryItem, newItem: CategoryItem
        ): Boolean {
            return oldItem.category == newItem.category &&
                    oldItem.label == newItem.label
        }

        override fun areContentsTheSame(
            oldItem: CategoryItem, newItem: CategoryItem
        ): Boolean {
            return oldItem.category == newItem.category &&
                    oldItem.label == newItem.label
        }
    }
}