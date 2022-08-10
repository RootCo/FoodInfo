package com.example.foodinfo.repository.model

import androidx.recyclerview.widget.DiffUtil


data class RecipeNutrientModel(
    val id: Long,
    val label: String,
    val totalValue: String,
    val dailyValue: Int
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
                    oldItem.totalValue == oldItem.totalValue &&
                    oldItem.dailyValue == oldItem.dailyValue
        }
    }
}