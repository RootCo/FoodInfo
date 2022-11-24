package com.example.foodinfo.repository.model

import androidx.recyclerview.widget.DiffUtil


data class CategoryModel(
    val id: Long,
    val name: String,
    val description: String,
    val preview: SVGModel
) {
    object ItemCallBack :
        DiffUtil.ItemCallback<CategoryModel>() {

        override fun areItemsTheSame(
            oldItem: CategoryModel,
            newItem: CategoryModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: CategoryModel,
            newItem: CategoryModel
        ): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.name == newItem.name &&
                    oldItem.description == newItem.description &&
                    oldItem.preview.content == newItem.preview.content
        }
    }
}