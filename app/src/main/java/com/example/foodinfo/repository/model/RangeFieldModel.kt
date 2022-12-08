package com.example.foodinfo.repository.model

import androidx.recyclerview.widget.DiffUtil


data class RangeFieldModel(
    val id: Long,
    val name: String,
    val measure: String,
    val category: String,
    val stepSize: Float,
    val minValue: Float,
    val maxValue: Float,
    var minCurrent: Float,
    var maxCurrent: Float
) {

    object ItemCallBack :
        DiffUtil.ItemCallback<RangeFieldModel>() {

        override fun areItemsTheSame(
            oldItem: RangeFieldModel,
            newItem: RangeFieldModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: RangeFieldModel,
            newItem: RangeFieldModel
        ): Boolean {
            return oldItem.name == newItem.name &&
                    oldItem.measure == newItem.measure &&
                    oldItem.category == newItem.category &&
                    oldItem.stepSize == newItem.stepSize &&
                    oldItem.minValue == newItem.minValue &&
                    oldItem.maxValue == newItem.maxValue &&
                    oldItem.minCurrent == newItem.minCurrent &&
                    oldItem.maxCurrent == newItem.maxCurrent
        }
    }
}