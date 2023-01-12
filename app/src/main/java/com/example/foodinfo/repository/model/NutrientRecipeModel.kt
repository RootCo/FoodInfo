package com.example.foodinfo.repository.model

import androidx.recyclerview.widget.DiffUtil


data class NutrientRecipeModel(
    val id: Long,
    val fieldInfo: NutrientFieldModel,
    val totalWeight: Float,
    val dailyPercent: Int,
) {

    object ItemCallBack :
        DiffUtil.ItemCallback<NutrientRecipeModel>() {

        override fun areItemsTheSame(
            oldItem: NutrientRecipeModel,
            newItem: NutrientRecipeModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: NutrientRecipeModel,
            newItem: NutrientRecipeModel
        ): Boolean {
            return oldItem.fieldInfo == newItem.fieldInfo &&
                    oldItem.totalWeight == newItem.totalWeight &&
                    oldItem.dailyPercent == newItem.dailyPercent
        }
    }
}