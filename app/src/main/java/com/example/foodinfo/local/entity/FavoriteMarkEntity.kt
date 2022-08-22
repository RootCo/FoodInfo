package com.example.foodinfo.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    tableName = FavoriteMarkEntity.TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = RecipeEntity::class,
        parentColumns = arrayOf(RecipeEntity.Columns.ID),
        childColumns = arrayOf(FavoriteMarkEntity.Columns.RECIPE_ID),
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
    )]
)
data class FavoriteMarkEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Columns.ID)
    val id: Long = 0,

    @ColumnInfo(name = Columns.RECIPE_ID)
    val recipeId: String,

    @ColumnInfo(name = Columns.IS_FAVORITE)
    val isFavorite: Boolean
) {
    object Columns {
        const val ID = "id"
        const val RECIPE_ID = "favorite_recipe_id"
        const val IS_FAVORITE = "is_favorite"

    }

    companion object {
        const val TABLE_NAME = "favorite_mark"
    }
}