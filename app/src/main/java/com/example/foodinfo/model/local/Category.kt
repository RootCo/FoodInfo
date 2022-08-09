package com.example.foodinfo.model.local

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil


data class Category(
    val category: String,
    val labels: List<Label>,
    var state: Parcelable? = null
) {
    object ItemCallBack :
        DiffUtil.ItemCallback<Category>() {

        override fun areItemsTheSame(
            oldItem: Category,
            newItem: Category
        ): Boolean {
            return oldItem.category == newItem.category
        }

        override fun areContentsTheSame(
            oldItem: Category,
            newItem: Category
        ): Boolean {
            return oldItem.category == newItem.category &&
                    oldItem.labels == newItem.labels
        }
    }
}