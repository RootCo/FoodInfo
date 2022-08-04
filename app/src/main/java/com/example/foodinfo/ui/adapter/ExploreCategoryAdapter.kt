package com.example.foodinfo.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.foodinfo.model.local.CategoryItem
import com.example.foodinfo.ui.view_holder.ExploreViewHolder


class ExploreCategoryAdapter(
    context: Context,
    private val onItemClickListener: (String, String) -> Unit
) : ListAdapter<CategoryItem, ViewHolder>(CategoryItem.ItemCallBack) {

    private val layoutInflater = LayoutInflater.from(context)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ExploreViewHolder(
            ExploreViewHolder.createView(layoutInflater, parent),
            onItemClickListener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { recipes ->
            holder as ExploreViewHolder
            holder.bind(recipes)
        }
    }
}