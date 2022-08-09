package com.example.foodinfo.local.model

import androidx.recyclerview.widget.DiffUtil
import com.example.foodinfo.local.entity.RecipeNutrientEntity


data class RecipeNutrient(
    val id: Long,
    val label: String,
    val totalValue: String,
    val dailyValue: Int
) {

    object ItemCallBack :
        DiffUtil.ItemCallback<RecipeNutrient>() {

        override fun areItemsTheSame(
            oldItem: RecipeNutrient,
            newItem: RecipeNutrient
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: RecipeNutrient,
            newItem: RecipeNutrient
        ): Boolean {
            return oldItem.label == newItem.label &&
                    oldItem.totalValue == oldItem.totalValue &&
                    oldItem.dailyValue == oldItem.dailyValue
        }
    }

    companion object {
        fun fromEntity(entity: RecipeNutrientEntity): RecipeNutrient {
            return RecipeNutrient(
                id = entity.id,
                label = entity.label,
                totalValue = "${entity.totalValue} ${entity.unit}",
                dailyValue = entity.dailyValue.toInt()
            )
        }
    }
}