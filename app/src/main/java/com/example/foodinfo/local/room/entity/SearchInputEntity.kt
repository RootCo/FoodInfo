package com.example.foodinfo.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.foodinfo.local.dto.SearchInputDB


@Entity(
    tableName = SearchInputEntity.TABLE_NAME,
    indices = [Index(value = arrayOf(SearchInputEntity.Columns.INPUT_TEXT), unique = true)]
)
data class SearchInputEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Columns.ID)
    override val ID: Int = 0,

    @ColumnInfo(name = Columns.INPUT_TEXT)
    override val inputText: String,

    @ColumnInfo(name = Columns.DATE)
    override val date: Long = System.currentTimeMillis()

) : SearchInputDB(
    ID = ID,
    inputText = inputText,
    date = date
) {

    object Columns {
        const val ID = "id"
        const val INPUT_TEXT = "input_text"
        const val DATE = "date"
    }

    companion object {
        const val TABLE_NAME = "search_input"
        const val LIMIT = 7

        fun fromDB(item: SearchInputDB): SearchInputEntity {
            return SearchInputEntity(
                ID = item.ID,
                inputText = item.inputText,
                date = item.date
            )
        }
    }
}