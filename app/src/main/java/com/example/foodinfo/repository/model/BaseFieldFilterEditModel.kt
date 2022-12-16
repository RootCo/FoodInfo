package com.example.foodinfo.repository.model

import androidx.recyclerview.widget.DiffUtil


data class BaseFieldFilterEditModel(
    val id: Long,
    val name: String,
    val measure: String,
    val rangeMin: Float,
    val rangeMax: Float,
    val minValue: Float,
    val maxValue: Float
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
                    oldItem.rangeMin == newItem.rangeMin &&
                    oldItem.rangeMax == newItem.rangeMax &&
                    oldItem.minValue == newItem.minValue &&
                    oldItem.maxValue == newItem.maxValue
        }
    }
}