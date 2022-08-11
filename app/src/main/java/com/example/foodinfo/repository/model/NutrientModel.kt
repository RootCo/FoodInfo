package com.example.foodinfo.repository.model

import androidx.recyclerview.widget.DiffUtil


data class NutrientModel(
    val id: Long,
    val tag: String,
    val label: String,
    val description: String,
    val preview: SVGModel
) {

    object ItemCallBack :
        DiffUtil.ItemCallback<NutrientModel>() {

        override fun areItemsTheSame(
            oldItem: NutrientModel,
            newItem: NutrientModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: NutrientModel,
            newItem: NutrientModel
        ): Boolean {
            return oldItem.label == newItem.label &&
                    oldItem.tag == oldItem.tag &&
                    oldItem.description == oldItem.description &&
                    oldItem.preview.content == newItem.preview.content
        }
    }
}