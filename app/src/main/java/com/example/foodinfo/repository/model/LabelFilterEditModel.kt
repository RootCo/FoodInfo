package com.example.foodinfo.repository.model

import androidx.recyclerview.widget.DiffUtil


data class LabelFilterEditModel(
    val id: Long,
    val name: String,
    var isSelected: Boolean
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
            return oldItem.name == newItem.name &&
                    oldItem.isSelected == newItem.isSelected
        }
    }
}