package com.example.foodinfo.repository.model

import androidx.recyclerview.widget.DiffUtil


data class NutrientFilterEditModel(
    val id: Long,
    val name: String,
    val measure: String,
    val rangeMin: Float,
    val rangeMax: Float,
    val minValue: Float,
    val maxValue: Float
) {

    object ItemCallBack :
        DiffUtil.ItemCallback<NutrientFilterEditModel>() {

        override fun areItemsTheSame(
            oldItem: NutrientFilterEditModel,
            newItem: NutrientFilterEditModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: NutrientFilterEditModel,
            newItem: NutrientFilterEditModel
        ): Boolean {
            return oldItem.name == newItem.name &&
                    oldItem.measure == newItem.measure &&
                    oldItem.rangeMin == newItem.rangeMin &&
                    oldItem.rangeMax == newItem.rangeMax &&
                    oldItem.minValue == newItem.minValue &&
                    oldItem.maxValue == newItem.maxValue
        }
    }
}