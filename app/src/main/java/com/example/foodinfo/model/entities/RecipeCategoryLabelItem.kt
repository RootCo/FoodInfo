package com.example.foodinfo.model.entities

import androidx.recyclerview.widget.DiffUtil

data class RecipeCategoryLabelItem(
    val label: String, val recipes: List<RecipeShort>
) {
    object ItemCallBack :
        DiffUtil.ItemCallback<RecipeCategoryLabelItem>() {

        override fun areItemsTheSame(
            oldItem: RecipeCategoryLabelItem, newItem: RecipeCategoryLabelItem
        ): Boolean {
            return oldItem.label == newItem.label
        }

        override fun areContentsTheSame(
            oldItem: RecipeCategoryLabelItem, newItem: RecipeCategoryLabelItem
        ): Boolean {
            return oldItem.label == newItem.label
        }

    }
}