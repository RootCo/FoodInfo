package com.example.foodinfo.repository.model

import androidx.recyclerview.widget.DiffUtil


data class CategoryFieldModel(
    val id: Long,
    val name: String,
    val preview: SVGModel
) {
    object ItemCallBack :
        DiffUtil.ItemCallback<CategoryFieldModel>() {

        override fun areItemsTheSame(
            oldItem: CategoryFieldModel,
            newItem: CategoryFieldModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: CategoryFieldModel,
            newItem: CategoryFieldModel
        ): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.name == newItem.name &&
                    oldItem.preview.content == newItem.preview.content
        }
    }
}