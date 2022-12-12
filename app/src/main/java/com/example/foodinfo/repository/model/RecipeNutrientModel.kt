package com.example.foodinfo.repository.model

import androidx.recyclerview.widget.DiffUtil


data class RecipeNutrientModel(
    val id: Long,
    val label: String,
    val measure: String,
    val totalWeight: Double,
    val dailyWeight: Double,
    val dailyPercent: Int,
) {

    object ItemCallBack :
        DiffUtil.ItemCallback<RecipeNutrientModel>() {

        override fun areItemsTheSame(
            oldItem: RecipeNutrientModel,
            newItem: RecipeNutrientModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: RecipeNutrientModel,
            newItem: RecipeNutrientModel
        ): Boolean {
            return oldItem.label == newItem.label &&
                    oldItem.totalWeight == newItem.totalWeight &&
                    oldItem.dailyPercent == newItem.dailyPercent
        }
    }
}