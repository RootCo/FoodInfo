package com.example.foodinfo.local.model

import androidx.recyclerview.widget.DiffUtil


data class LabelModel(
    val id: Long,
    val category: String,
    val label: String,
    val description: String,
    val previewURL: String
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
            return oldItem.label == newItem.label
        }
    }
}