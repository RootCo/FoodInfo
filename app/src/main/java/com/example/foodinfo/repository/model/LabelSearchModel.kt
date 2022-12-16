package com.example.foodinfo.repository.model

import androidx.recyclerview.widget.DiffUtil


data class LabelSearchModel(
    val id: Long,
    val name: String,
    val preview: SVGModel
) {

    object ItemCallBack :
        DiffUtil.ItemCallback<LabelSearchModel>() {

        override fun areItemsTheSame(
            oldItem: LabelSearchModel,
            newItem: LabelSearchModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: LabelSearchModel,
            newItem: LabelSearchModel
        ): Boolean {
            return oldItem.name == newItem.name &&
                    oldItem.preview == newItem.preview
        }
    }
}