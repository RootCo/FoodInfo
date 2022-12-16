package com.example.foodinfo.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
    tableName = CategoryFieldEntity.TABLE_NAME,
    indices = [Index(value = arrayOf(CategoryFieldEntity.Columns.NAME), unique = true)]
)
data class CategoryFieldEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Columns.ID)
    val id: Long = 0,

    @ColumnInfo(name = Columns.NAME)
    val name: String,

    @ColumnInfo(name = Columns.PREVIEW_URL)
    val previewURL: String,
) {

    object Columns {
        const val ID = "id"
        const val NAME = "name"
        const val PREVIEW_URL = "preview_url"
    }

    companion object {
        const val TABLE_NAME = "categories"
    }
}