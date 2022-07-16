package com.example.foodinfo.model.local.entities

import androidx.recyclerview.widget.DiffUtil
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey


/*
    требуемые доработки:
    - время и дата запроса
    - уникальность поля inputText
 */
@Entity(tableName = SearchInput.TABLE_NAME)
data class SearchInput(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Columns.ID)
    val id: Long,

    @ColumnInfo(name = Columns.INPUT_TEXT)
    val inputText: String,
) {
    @Ignore
    val date: String = ""

    object ItemCallBack : DiffUtil.ItemCallback<SearchInput>() {

        override fun areItemsTheSame(
            oldItem: SearchInput, newItem: SearchInput
        ): Boolean {
            return oldItem.inputText == newItem.inputText
        }

        override fun areContentsTheSame(
            oldItem: SearchInput, newItem: SearchInput
        ): Boolean {
            return oldItem.inputText == newItem.inputText
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
    }
}