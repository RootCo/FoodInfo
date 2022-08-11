package com.example.foodinfo.repository.model

import androidx.recyclerview.widget.DiffUtil


data class LabelModel(
    val id: Long,
    val category: String,
    val label: String,
    val description: String,
    val preview: SVGModel
) {

    object ItemCallBack :
        DiffUtil.ItemCallback<LabelModel>() {

        override fun areItemsTheSame(
            oldItem: LabelModel,
            newItem: LabelModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: LabelModel,
            newItem: LabelModel
        ): Boolean {
            return oldItem.category == newItem.category &&
                    oldItem.label == newItem.label &&
                    oldItem.description == newItem.description &&
                    oldItem.preview.content == newItem.preview.content
        }
    }
}