package com.example.foodinfo.model.local

import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.DiffUtil
import com.example.foodinfo.model.local.entities.recipe_field.RecipeIngredientEntity
import com.example.foodinfo.utils.ResourcesProvider


data class RecipeIngredient(
    val id: Long,
    val text: String,
    val quantity: String,
    val weight: String,
    val food: String,
    val foodCategory: String,
    val foodId: String,
    val preview: Drawable? = null
) {

    object ItemCallBack :
        DiffUtil.ItemCallback<RecipeIngredient>() {

        override fun areItemsTheSame(
            oldItem: RecipeIngredient,
            newItem: RecipeIngredient
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: RecipeIngredient,
            newItem: RecipeIngredient
        ): Boolean {
            return oldItem.text == newItem.text &&
                    oldItem.quantity == oldItem.quantity &&
                    oldItem.weight == oldItem.weight &&
                    oldItem.food == oldItem.food &&
                    oldItem.foodCategory == oldItem.foodCategory &&
                    oldItem.foodId == oldItem.foodId
        }
    }

    companion object {
        fun fromEntity(
            entity: RecipeIngredientEntity,
            resourcesProvider: ResourcesProvider,
        ): RecipeIngredient {
            return RecipeIngredient(
                id = entity.id,
                text = entity.text,
                quantity = "${entity.quantity} ${entity.measure}",
                weight = "${entity.weight}g",
                food = entity.food,
                foodId = entity.foodId,
                foodCategory = entity.foodCategory,
                preview = resourcesProvider.getDrawableByName(entity.previewURL)
            )
        }
    }
}