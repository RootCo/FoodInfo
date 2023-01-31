package com.example.foodinfo.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.foodinfo.local.dto.CategoryRecipeAttrDB


@Entity(
    tableName = CategoryRecipeAttrEntity.TABLE_NAME,
    indices = [Index(value = arrayOf(CategoryRecipeAttrEntity.Columns.ID), unique = true)]
)
data class CategoryRecipeAttrEntity(
    @PrimaryKey
    @ColumnInfo(name = Columns.ID)
    override val ID: Int,

    @ColumnInfo(name = Columns.TAG)
    override val tag: String,

    @ColumnInfo(name = Columns.NAME)
    override val name: String,

    @ColumnInfo(name = Columns.PREVIEW_URL)
    override val previewURL: String

) : CategoryRecipeAttrDB(
    ID = ID,
    tag = tag,
    name = name,
    previewURL = previewURL
) {

    object Columns {
        const val ID = "id"
        const val TAG = "tag"
        const val NAME = "name"
        const val PREVIEW_URL = "preview_url"
    }

    companion object {
        const val TABLE_NAME = "category_recipe_attr"

        fun fromDB(item: CategoryRecipeAttrDB): CategoryRecipeAttrEntity {
            return CategoryRecipeAttrEntity(
                ID = item.ID,
                tag = item.tag,
                name = item.name,
                previewURL = item.previewURL
            )
        }
    }
}