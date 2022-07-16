package com.example.foodinfo.model.local

import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.DiffUtil
import androidx.room.ColumnInfo
import androidx.room.Ignore
import com.example.foodinfo.model.local.entities.Recipe


data class RecipeExplore(
    @ColumnInfo(name = Recipe.Columns.ID)
    val id: String,

    @ColumnInfo(name = Recipe.Columns.NAME)
    val name: String,

    @ColumnInfo(name = Recipe.Columns.CALORIES)
    val calories: Int,

    @ColumnInfo(name = Recipe.Columns.PREVIEW_URL)
    val previewURL: String,
) {
    @Ignore
    var preview: Drawable? = null

    object ItemCallBack : DiffUtil.ItemCallback<RecipeExplore>() {
        override fun areItemsTheSame(
            oldItem: RecipeExplore, newItem: RecipeExplore
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: RecipeExplore, newItem: RecipeExplore
        ): Boolean {
            return oldItem.name == newItem.name && oldItem.calories == newItem.calories
        }
    }

    companion object {
        const val SELECTOR =
            "SELECT " +
                    "${Recipe.Columns.ID}, " +
                    "${Recipe.Columns.NAME}, " +
                    "${Recipe.Columns.CALORIES}, " +
                    "${Recipe.Columns.PREVIEW_URL} " +
                    "FROM ${Recipe.TABLE_NAME}"
    }
}