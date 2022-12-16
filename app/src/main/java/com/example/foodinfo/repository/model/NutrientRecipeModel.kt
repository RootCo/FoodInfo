package com.example.foodinfo.repository.model

import androidx.recyclerview.widget.DiffUtil


data class NutrientRecipeModel(
    val id: Long,
    val label: String,
    val measure: String,
    val totalWeight: Float,
    val dailyWeight: Float,
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
            return oldItem.label == newItem.label &&
                    oldItem.totalWeight == newItem.totalWeight &&
                    oldItem.dailyPercent == newItem.dailyPercent
        }
    }
}