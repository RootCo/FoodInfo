package com.example.foodinfo.model.local

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil


data class CategoryItem(
    val category: String,
    var labels: List<LabelItem>,
    var state: Parcelable? = null
) {
    object ItemCallBack :
        DiffUtil.ItemCallback<CategoryItem>() {

        override fun areItemsTheSame(
            oldItem: CategoryItem,
            newItem: CategoryItem
        ): Boolean {
            return oldItem.category == newItem.category
        }

        override fun areContentsTheSame(
            oldItem: CategoryItem,
            newItem: CategoryItem
        ): Boolean {
            return oldItem.category == newItem.category &&
                    oldItem.labels == newItem.labels
        }
    }
}