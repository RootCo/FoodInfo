package com.example.foodinfo.repository.model

import androidx.recyclerview.widget.DiffUtil
import com.example.foodinfo.local.entity.SearchFilterEntity


data class SearchFilterModel(
    val id: Long = 0,
    val name: String = SearchFilterEntity.DEFAULT_NAME
) {

    object ItemCallBack :
        DiffUtil.ItemCallback<SearchFilterModel>() {

        override fun areItemsTheSame(
            oldItem: SearchFilterModel,
            newItem: SearchFilterModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: SearchFilterModel,
            newItem: SearchFilterModel
        ): Boolean {
            return oldItem.name == newItem.name
        }
    }
}