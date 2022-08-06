package com.example.foodinfo.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.foodinfo.model.local.entities.SearchInput
import com.example.foodinfo.ui.view_holder.SearchInputViewHolder


class SearchInputAdapter(
    context: Context,
    private val onArrowClickListener: (String) -> Unit,
    private val onItemClickListener: (String) -> Unit
) : ListAdapter<SearchInput, ViewHolder>(SearchInput.ItemCallBack) {

    private val layoutInflater = LayoutInflater.from(context)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return SearchInputViewHolder(
            SearchInputViewHolder.createView(layoutInflater, parent),
            onArrowClickListener,
            onItemClickListener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { searchInput ->
            holder as SearchInputViewHolder
            holder.bind(searchInput)
        }
    }
}