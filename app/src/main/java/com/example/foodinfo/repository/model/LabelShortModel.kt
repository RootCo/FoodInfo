package com.example.foodinfo.repository.model

import androidx.recyclerview.widget.DiffUtil


data class LabelShortModel(
    val id: Long,
    val name: String
) {

    object ItemCallBack :
        DiffUtil.ItemCallback<LabelShortModel>() {

        override fun areItemsTheSame(
            oldItem: LabelShortModel,
            newItem: LabelShortModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: LabelShortModel,
            newItem: LabelShortModel
        ): Boolean {
            return oldItem.name == newItem.name
        }
    }
}