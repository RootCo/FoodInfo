package com.example.foodinfo.repository.model

import androidx.recyclerview.widget.DiffUtil


data class RecipeCategoryModel(
    val name: String,
    val labels: List<String>
) {

    object ItemCallBack :
        DiffUtil.ItemCallback<RecipeCategoryModel>() {

        override fun areItemsTheSame(
            oldItem: RecipeCategoryModel,
            newItem: RecipeCategoryModel
        ): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: RecipeCategoryModel,
            newItem: RecipeCategoryModel
        ): Boolean {
            return oldItem.name == newItem.name &&
                    oldItem.labels == newItem.labels
        }
    }
}
