package com.example.foodinfo.repository.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil


data class CategoryModel(
    val category: String,
    val labels: List<LabelModel>,
    var state: Parcelable? = null
) {
    object ItemCallBack :
        DiffUtil.ItemCallback<CategoryModel>() {

        override fun areItemsTheSame(
            oldItem: CategoryModel,
            newItem: CategoryModel
        ): Boolean {
            return oldItem.category == newItem.category
        }

        override fun areContentsTheSame(
            oldItem: CategoryModel,
            newItem: CategoryModel
        ): Boolean {
            return oldItem.category == newItem.category &&
                    oldItem.labels == newItem.labels
        }
    }
}