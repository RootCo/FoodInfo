package com.example.foodinfo.repository.model

import androidx.recyclerview.widget.DiffUtil


data class CategoryFilterPreviewModel(
    val name: String,
    val labels: List<LabelShortModel>
) {

    object ItemCallBack : DiffUtil.ItemCallback<CategoryFilterPreviewModel>() {
        override fun areItemsTheSame(
            oldItem: CategoryFilterPreviewModel,
            newItem: CategoryFilterPreviewModel
        ): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: CategoryFilterPreviewModel,
            newItem: CategoryFilterPreviewModel
        ): Boolean {
            return oldItem.name == newItem.name &&
                    oldItem.labels == newItem.labels
        }
    }
}
