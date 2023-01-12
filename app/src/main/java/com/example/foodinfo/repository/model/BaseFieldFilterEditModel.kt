package com.example.foodinfo.repository.model

import androidx.recyclerview.widget.DiffUtil


data class BaseFieldFilterEditModel(
    val id: Long,
    val fieldInfo: BaseFieldModel,
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
            return oldItem.fieldInfo == newItem.fieldInfo &&
                    oldItem.minValue == newItem.minValue &&
                    oldItem.maxValue == newItem.maxValue
        }
    }
}