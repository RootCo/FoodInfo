package com.example.foodinfo.model.local

import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.DiffUtil


data class LabelItem(
    val category: String,
    val label: String,
    val icon: Drawable
) {

    object ItemCallBack :
        DiffUtil.ItemCallback<LabelItem>() {

        override fun areItemsTheSame(
            oldItem: LabelItem,
            newItem: LabelItem
        ): Boolean {
            return oldItem.category == newItem.category &&
                    oldItem.label == newItem.label
        }

        override fun areContentsTheSame(
            oldItem: LabelItem,
            newItem: LabelItem
        ): Boolean {
            return oldItem.category == newItem.category &&
                    oldItem.label == newItem.label
        }
    }
}