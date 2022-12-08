package com.example.foodinfo.repository.model

import androidx.recyclerview.widget.DiffUtil


data class LabelFilterEditModel(
    val id: Long,
    val label: String,
    var selected: Boolean = false
) {

    object ItemCallBack :
        DiffUtil.ItemCallback<LabelFilterEditModel>() {

        override fun areItemsTheSame(
            oldItem: LabelFilterEditModel,
            newItem: LabelFilterEditModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: LabelFilterEditModel,
            newItem: LabelFilterEditModel
        ): Boolean {
            return oldItem.label == newItem.label &&
                    oldItem.selected == newItem.selected
        }
    }
}