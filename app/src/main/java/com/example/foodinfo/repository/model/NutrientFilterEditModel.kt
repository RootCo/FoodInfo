package com.example.foodinfo.repository.model

import androidx.recyclerview.widget.DiffUtil


data class NutrientFilterEditModel(
    val id: Long,
    val fieldInfo: NutrientFieldModel,
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
            return oldItem.fieldInfo == newItem.fieldInfo &&
                    oldItem.minValue == newItem.minValue &&
                    oldItem.maxValue == newItem.maxValue
        }
    }
}