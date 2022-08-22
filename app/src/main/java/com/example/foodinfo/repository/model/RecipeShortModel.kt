package com.example.foodinfo.repository.model

import androidx.recyclerview.widget.DiffUtil


data class RecipeShortModel(
    val id: String,
    val name: String,
    val calories: String,
    val servings: String,
    val totalTime: Int,
    val totalIngredients: String,
    val previewURL: String,
    val isFavorite: Boolean
) {

    object ItemCallBack : DiffUtil.ItemCallback<RecipeShortModel>() {
        override fun areItemsTheSame(
            oldItem: RecipeShortModel, newItem: RecipeShortModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: RecipeShortModel, newItem: RecipeShortModel
        ): Boolean {
            return oldItem.name == newItem.name &&
                    oldItem.calories == newItem.calories &&
                    oldItem.servings == newItem.servings &&
                    oldItem.totalTime == newItem.totalTime &&
                    oldItem.totalIngredients == newItem.totalIngredients &&
                    oldItem.previewURL == newItem.previewURL &&
                    oldItem.isFavorite == newItem.isFavorite

        }

        override fun getChangePayload(
            oldItem: RecipeShortModel,
            newItem: RecipeShortModel
        ): Any? {
            if (oldItem.isFavorite != newItem.isFavorite) return newItem.isFavorite
            return super.getChangePayload(oldItem, newItem)
        }
    }
}