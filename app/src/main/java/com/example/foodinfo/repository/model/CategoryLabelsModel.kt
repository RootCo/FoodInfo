package com.example.foodinfo.repository.model

import androidx.recyclerview.widget.DiffUtil


data class CategoryLabelsModel(
    val name: String,
    val labels: List<String>
) {

    object ItemCallBack :
        DiffUtil.ItemCallback<CategoryLabelsModel>() {

        override fun areItemsTheSame(
            oldItem: CategoryLabelsModel,
            newItem: CategoryLabelsModel
        ): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: CategoryLabelsModel,
            newItem: CategoryLabelsModel
        ): Boolean {
            return oldItem.name == newItem.name &&
                    oldItem.labels == newItem.labels
        }
    }
}
