package com.example.foodinfo.model.entities

import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.DiffUtil

data class RecipeShort(
    val id: String,
    val name: String,
    val calories: Int,
    val previewURL: String,
    var preview: Drawable?
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
            return oldItem.name == newItem.name && oldItem.calories == newItem.calories
        }
    }
}