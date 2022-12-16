package com.example.foodinfo.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
    tableName = SearchFilterEntity.TABLE_NAME,
    indices = [Index(value = arrayOf(SearchFilterEntity.Columns.NAME), unique = true)]
)
data class SearchFilterEntity(
    @ColumnInfo(name = Columns.ID)
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = Columns.NAME)
    val name: String,

    @ColumnInfo(name = Columns.INPUT_TEXT)
    val inputText: String = ""
) {
    object Columns {
        const val ID = "id"
        const val NAME = "name"
        const val INPUT_TEXT = "input_text"
    }

    companion object {
        const val TABLE_NAME = "filter_search"
    }
}