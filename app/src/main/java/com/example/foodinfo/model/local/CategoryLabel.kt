package com.example.foodinfo.model.local

import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.DiffUtil
import com.example.foodinfo.model.local.entities.CategoryLabelEntity
import com.example.foodinfo.utils.ResourcesProvider


data class CategoryLabel(
    val id: Long,
    val category: String,
    val label: String,
    val description: String,
    val icon: Drawable
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
        fun fromEntity(
            entity: CategoryLabelEntity,
            resourcesProvider: ResourcesProvider
        ): CategoryLabel {
            return CategoryLabel(
                id = entity.id,
                category = entity.category,
                label = entity.label,
                description = entity.description,
                icon = resourcesProvider.getDrawableByName(entity.previewURL)!!
            )
        }
    }
}