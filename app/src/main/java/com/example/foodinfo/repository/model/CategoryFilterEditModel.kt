package com.example.foodinfo.repository.model

import androidx.recyclerview.widget.DiffUtil


data class CategoryFilterEditModel(
    val name: String,
    val labels: List<LabelFilterEditModel>
) {

    object ItemCallBack :
        DiffUtil.ItemCallback<CategoryFilterEditModel>() {

        override fun areItemsTheSame(
            oldItem: CategoryFilterEditModel,
            newItem: CategoryFilterEditModel
        ): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: CategoryFilterEditModel,
            newItem: CategoryFilterEditModel
        ): Boolean {
            return oldItem.name == newItem.name &&
                    oldItem.labels == newItem.labels
        }
    }
}
