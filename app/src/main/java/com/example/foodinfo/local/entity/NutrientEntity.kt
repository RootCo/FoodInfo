package com.example.foodinfo.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = NutrientEntity.TABLE_NAME)
data class NutrientEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Columns.ID)
    val id: Long = 0,

    @ColumnInfo(name = Columns.TAG)
    val tag: String,

    @ColumnInfo(name = Columns.LABEL)
    val label: String,

    @ColumnInfo(name = Columns.DESCRIPTION)
    val description: String,

    @ColumnInfo(name = Columns.PREVIEW_URL)
    val previewURL: String,
) {

    object Columns {
        const val ID = "id"
        const val TAG = "tag"
        const val LABEL = "label"
        const val DESCRIPTION = "description"
        const val PREVIEW_URL = "preview_url"
    }

    companion object {
        const val TABLE_NAME = "nutrients"
        const val SELECTOR = "SELECT * FROM $TABLE_NAME"
    }
}