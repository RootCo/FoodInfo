package com.example.foodinfo.repository.model

import androidx.recyclerview.widget.DiffUtil


data class FilterNutrientModel(
    val id: Long,
    val name: String,
    val measure: String,
    val minValue: Float,
    val maxValue: Float
) {

    object ItemCallBack :
        DiffUtil.ItemCallback<FilterNutrientModel>() {

        override fun areItemsTheSame(
            oldItem: FilterNutrientModel,
            newItem: FilterNutrientModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: FilterNutrientModel,
            newItem: FilterNutrientModel
        ): Boolean {
            return oldItem.name == newItem.name &&
                    oldItem.measure == newItem.measure &&
                    oldItem.minValue == newItem.minValue &&
                    oldItem.maxValue == newItem.maxValue
        }
    }
}