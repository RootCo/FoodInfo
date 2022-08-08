package com.example.foodinfo.model.local

import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.DiffUtil
import com.example.foodinfo.model.local.entities.NutrientEntity
import com.example.foodinfo.utils.ResourcesProvider


data class Nutrient(
    val id: Long,
    val tag: String,
    val label: String,
    val description: String,
    val icon: Drawable?
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
                    oldItem.description == oldItem.description
        }
    }

    companion object {
        fun fromEntity(
            entity: NutrientEntity,
            resourcesProvider: ResourcesProvider
        ): Nutrient {
            return Nutrient(
                id = entity.id,
                tag = entity.tag,
                label = entity.label,
                description = entity.description,
                icon = resourcesProvider.getDrawableByName(entity.previewURL)
            )
        }
    }
}