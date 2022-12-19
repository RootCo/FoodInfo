package com.example.foodinfo.repository.model

import androidx.recyclerview.widget.DiffUtil


data class CategoryRecipeModel(
    val name: String,
    val labels: List<LabelShortModel>
) {

    object ItemCallBack :
        DiffUtil.ItemCallback<CategoryRecipeModel>() {

        override fun areItemsTheSame(
            oldItem: CategoryRecipeModel,
            newItem: CategoryRecipeModel
        ): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: CategoryRecipeModel,
            newItem: CategoryRecipeModel
        ): Boolean {
            return oldItem.name == newItem.name &&
                    oldItem.labels == newItem.labels
        }
    }
}
