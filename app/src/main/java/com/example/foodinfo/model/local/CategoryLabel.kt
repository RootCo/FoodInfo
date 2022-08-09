package com.example.foodinfo.model.local

import androidx.recyclerview.widget.DiffUtil
import com.example.foodinfo.model.local.entities.CategoryLabelEntity


data class CategoryLabel(
    val id: Long,
    val category: String,
    val label: String,
    val description: String,
    val previewURL: String
) {

    object ItemCallBack :
        DiffUtil.ItemCallback<CategoryLabel>() {

        override fun areItemsTheSame(
            oldItem: CategoryLabel,
            newItem: CategoryLabel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: CategoryLabel,
            newItem: CategoryLabel
        ): Boolean {
            return oldItem.label == newItem.label
        }
    }

    companion object {
        fun fromEntity(entity: CategoryLabelEntity): CategoryLabel {
            return CategoryLabel(
                id = entity.id,
                category = entity.category,
                label = entity.label,
                description = entity.description,
                previewURL = entity.previewURL
            )
        }
    }
}