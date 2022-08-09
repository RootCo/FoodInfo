package com.example.foodinfo.local.model

import androidx.recyclerview.widget.DiffUtil
import com.example.foodinfo.local.entity.SearchInputEntity


// choose best dateTime format and lib
data class SearchInput(
    val id: Long = 0,
    val inputText: String,
    val date: String = System.currentTimeMillis().toString()
) {

    object ItemCallBack : DiffUtil.ItemCallback<SearchInput>() {

        override fun areItemsTheSame(
            oldItem: SearchInput, newItem: SearchInput
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: SearchInput, newItem: SearchInput
        ): Boolean {
            return oldItem.inputText == newItem.inputText &&
                    oldItem.date == newItem.date
        }
    }

    companion object {
        fun fromEntity(entity: SearchInputEntity): SearchInput {
            return SearchInput(
                id = entity.id,
                inputText = entity.inputText,
                date = entity.date.toString() // implement proper conversion
            )
        }

        fun toEntity(searchInput: SearchInput): SearchInputEntity {
            return SearchInputEntity(
                id = searchInput.id,
                inputText = searchInput.inputText,
                date = searchInput.date.toLong() // implement proper conversion
            )
        }
    }
}