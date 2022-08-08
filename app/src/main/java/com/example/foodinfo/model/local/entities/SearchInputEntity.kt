package com.example.foodinfo.model.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
    tableName = SearchInputEntity.TABLE_NAME,
    indices = [Index(
        value = arrayOf(SearchInputEntity.Columns.INPUT_TEXT),
        unique = true
    )]
)
data class SearchInputEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Columns.ID)
    val id: Long = 0,

    @ColumnInfo(name = Columns.INPUT_TEXT)
    val inputText: String,

    @ColumnInfo(name = Columns.DATE)
    val date: Long = System.currentTimeMillis()
) {

    object Columns {
        const val ID = "id"
        const val INPUT_TEXT = "input_text"
        const val DATE = "date"
    }

    companion object {
        const val TABLE_NAME = "search_input"
        const val SELECTOR = "SELECT * FROM $TABLE_NAME"
        const val LIMIT = 7
    }
}