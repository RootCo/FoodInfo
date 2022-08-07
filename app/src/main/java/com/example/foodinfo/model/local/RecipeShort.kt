package com.example.foodinfo.model.local

import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.DiffUtil
import com.example.foodinfo.utils.ResourcesProvider


data class RecipeShort(
    val id: String,
    val name: String,
    val calories: String,
    val servings: String,
    val totalTime: String,
    val totalIngredients: String,
    val preview: Drawable? = null
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
                    oldItem.totalIngredients == newItem.totalIngredients

        }
    }

    companion object {
        fun fromDTO(
            recipe: RecipeShortDTO,
            resourcesProvider: ResourcesProvider
        ): RecipeShort {
            return RecipeShort(
                id = recipe.id,
                name = recipe.name,
                calories = recipe.calories.toString(),
                servings = recipe.servings.toString(),
                totalTime = recipe.totalTime.toString() + " min",
                totalIngredients = recipe.totalIngredients.toString(),
                preview = resourcesProvider.getDrawableByName(recipe.previewURL),
            )
        }
    }
}