package com.example.foodinfo.model.local

import androidx.recyclerview.widget.DiffUtil
import com.example.foodinfo.model.local.entities.LabelEntity


data class Label(
    val id: Long,
    val category: String,
    val label: String,
    val description: String,
    val previewURL: String
) {

    object ItemCallBack :
        DiffUtil.ItemCallback<Label>() {

        override fun areItemsTheSame(
            oldItem: Label,
            newItem: Label
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Label,
            newItem: Label
        ): Boolean {
            return oldItem.label == newItem.label
        }
    }

    companion object {
        fun fromEntity(entity: LabelEntity): Label {
            return Label(
                id = entity.id,
                category = entity.category,
                label = entity.label,
                description = entity.description,
                previewURL = entity.previewURL
            )
        }
    }
}