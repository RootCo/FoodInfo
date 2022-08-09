package com.example.foodinfo.local.model

import androidx.recyclerview.widget.DiffUtil
import com.example.foodinfo.local.entity.recipe.RecipeShortEntity


data class RecipeShort(
    val id: String,
    val name: String,
    val calories: String,
    val servings: String,
    val totalTime: String,
    val totalIngredients: String,
    val previewURL: String
) {

    object ItemCallBack : DiffUtil.ItemCallback<RecipeShort>() {
        override fun areItemsTheSame(
            oldItem: RecipeShort, newItem: RecipeShort
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: RecipeShort, newItem: RecipeShort
        ): Boolean {
            return oldItem.name == newItem.name &&
                    oldItem.calories == newItem.calories &&
                    oldItem.servings == newItem.servings &&
                    oldItem.totalTime == newItem.totalTime &&
                    oldItem.totalIngredients == newItem.totalIngredients &&
                    oldItem.previewURL == newItem.previewURL

        }
    }

    companion object {
        fun fromEntity(entity: RecipeShortEntity): RecipeShort {
            return RecipeShort(
                id = entity.id,
                name = entity.name,
                calories = entity.calories.toString(),
                servings = entity.servings.toString(),
                totalTime = "${entity.totalTime} min",
                totalIngredients = entity.totalIngredients.toString(),
                previewURL = entity.previewURL
            )
        }
    }
}