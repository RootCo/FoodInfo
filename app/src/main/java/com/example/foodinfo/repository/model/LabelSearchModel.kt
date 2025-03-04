package com.example.foodinfo.repository.model

import androidx.recyclerview.widget.DiffUtil


data class LabelSearchModel(
    val ID: Int,
    val name: String,
    val preview: SVGModel
) {

    object ItemCallBack :
        DiffUtil.ItemCallback<LabelSearchModel>() {

        override fun areItemsTheSame(
            oldItem: LabelSearchModel,
            newItem: LabelSearchModel
        ): Boolean {
            return oldItem.ID == newItem.ID
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