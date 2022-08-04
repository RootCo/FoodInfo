package com.example.foodinfo.model.local

import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.DiffUtil


data class CategoryItem(
    val category: String,
    val label: String,
    val icon: Drawable
) {

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