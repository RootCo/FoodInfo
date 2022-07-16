package com.example.foodinfo.model.local.entities

import androidx.recyclerview.widget.DiffUtil
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
    tableName = SearchInput.TABLE_NAME,
    indices = [Index(value = arrayOf(SearchInput.Columns.INPUT_TEXT), unique = true)]
)
data class SearchInput(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Columns.ID)
    val id: Long = 0,

    @ColumnInfo(name = Columns.INPUT_TEXT)
    val inputText: String,

    @ColumnInfo(name = Columns.DATE)
    val date: Long = System.currentTimeMillis()
) {

    object ItemCallBack : DiffUtil.ItemCallback<SearchInput>() {

        override fun areItemsTheSame(
            oldItem: SearchInput, newItem: SearchInput
        ): Boolean {
            return oldItem.inputText == newItem.inputText
        }

        override fun areContentsTheSame(
            oldItem: SearchInput, newItem: SearchInput
        ): Boolean {
            return oldItem.inputText == newItem.inputText &&
                    oldItem.date == newItem.date
        }
    }

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