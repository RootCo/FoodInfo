package com.example.foodinfo.model.entities

import androidx.recyclerview.widget.DiffUtil


/*
    изначально в SearchInputAdapter прилетал List<String>, теперь List<SearchInput>,
    в котором пока только одно поле String.
    вынес в отдельный класс чтобы в будущем можно было добавить поле
    date и сортировать по нему элементы
 */
data class SearchInput(val inputText: String) {
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
}