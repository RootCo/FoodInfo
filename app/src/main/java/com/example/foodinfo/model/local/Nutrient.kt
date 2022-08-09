package com.example.foodinfo.model.local

import androidx.recyclerview.widget.DiffUtil
import com.example.foodinfo.model.local.entities.NutrientEntity


data class Nutrient(
    val id: Long,
    val tag: String,
    val label: String,
    val description: String,
    val previewURL: String
) {

    object ItemCallBack :
        DiffUtil.ItemCallback<Nutrient>() {

        override fun areItemsTheSame(
            oldItem: Nutrient,
            newItem: Nutrient
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Nutrient,
            newItem: Nutrient
        ): Boolean {
            return oldItem.label == newItem.label &&
                    oldItem.tag == oldItem.tag &&
                    oldItem.description == oldItem.description &&
                    oldItem.previewURL == newItem.previewURL
        }
    }

    companion object {
        fun fromEntity(entity: NutrientEntity): Nutrient {
            return Nutrient(
                id = entity.id,
                tag = entity.tag,
                label = entity.label,
                description = entity.description,
                previewURL = entity.previewURL
            )
        }
    }
}