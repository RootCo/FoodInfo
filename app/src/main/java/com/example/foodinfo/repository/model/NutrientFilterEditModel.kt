package com.example.foodinfo.repository.model

import androidx.recyclerview.widget.DiffUtil


data class NutrientFilterEditModel(
    val id: Long,
    val name: String,
    val measure: String,
    val stepSize: Float,
    val rangeMin: Float,
    val rangeMax: Float,
    var minValue: Float,
    var maxValue: Float
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
                    oldItem.stepSize == newItem.stepSize &&
                    oldItem.rangeMin == newItem.rangeMin &&
                    oldItem.rangeMax == newItem.rangeMax &&
                    oldItem.minValue == newItem.minValue &&
                    oldItem.maxValue == newItem.maxValue
        }
    }
}