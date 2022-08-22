package com.example.foodinfo.repository.model

import androidx.recyclerview.widget.DiffUtil


data class RecipeFavoriteModel(
    val id: String,
    val name: String,
    val calories: String,
    val source: String,
    val servings: Int,
    val previewURL: String
) {

    object ItemCallBack : DiffUtil.ItemCallback<RecipeFavoriteModel>() {
        override fun areItemsTheSame(
            oldItem: RecipeFavoriteModel, newItem: RecipeFavoriteModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: RecipeFavoriteModel, newItem: RecipeFavoriteModel
        ): Boolean {
            return oldItem.name == newItem.name &&
                    oldItem.source == newItem.source &&
                    oldItem.calories == newItem.calories &&
                    oldItem.servings == newItem.servings &&
                    oldItem.previewURL == newItem.previewURL
        }
    }
}