package com.example.foodinfo.repository.model

import androidx.recyclerview.widget.DiffUtil


data class BaseFieldFilterEditModel(
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
        DiffUtil.ItemCallback<BaseFieldFilterEditModel>() {

        override fun areItemsTheSame(
            oldItem: BaseFieldFilterEditModel,
            newItem: BaseFieldFilterEditModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: BaseFieldFilterEditModel,
            newItem: BaseFieldFilterEditModel
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