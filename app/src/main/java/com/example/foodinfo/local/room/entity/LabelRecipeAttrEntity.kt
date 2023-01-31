package com.example.foodinfo.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.foodinfo.local.dto.LabelRecipeAttrDB


@Entity(
    tableName = LabelRecipeAttrEntity.TABLE_NAME,
    indices = [Index(value = arrayOf(LabelRecipeAttrEntity.Columns.ID), unique = true)]
)
data class LabelRecipeAttrEntity(
    @PrimaryKey
    @ColumnInfo(name = Columns.ID)
    override val ID: Int,

    @ColumnInfo(name = Columns.CATEGORY_ID)
    override val categoryID: Int,

    @ColumnInfo(name = Columns.TAG)
    override val tag: String,

    @ColumnInfo(name = Columns.NAME)
    override val name: String,

    @ColumnInfo(name = Columns.DESCRIPTION)
    override val description: String,

    @ColumnInfo(name = Columns.PREVIEW_URL)
    override val previewURL: String

) : LabelRecipeAttrDB(
    ID = ID,
    categoryID = categoryID,
    tag = tag,
    name = name,
    description = description,
    previewURL = previewURL
) {

    object Columns {
        const val ID = "id"
        const val CATEGORY_ID = "category_id"
        const val TAG = "tag"
        const val NAME = "name"
        const val DESCRIPTION = "description"
        const val PREVIEW_URL = "preview_url"
    }

    companion object {
        const val TABLE_NAME = "label_recipe_attr"

        fun fromDB(item: LabelRecipeAttrDB): LabelRecipeAttrEntity {
            return LabelRecipeAttrEntity(
                ID = item.ID,
                categoryID = item.categoryID,
                tag = item.tag,
                name = item.name,
                description = item.description,
                previewURL = item.previewURL
            )
        }
    }
}