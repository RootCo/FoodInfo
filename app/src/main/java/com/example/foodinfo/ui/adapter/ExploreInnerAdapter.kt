package com.example.foodinfo.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.foodinfo.model.local.CategoryLabel
import com.example.foodinfo.ui.view_holder.ExploreInnerViewHolder


class ExploreInnerAdapter(
    context: Context,
    private val onItemClickListener: (String, String) -> Unit
) : ListAdapter<CategoryLabel, ViewHolder>(CategoryLabel.ItemCallBack) {

    private val layoutInflater = LayoutInflater.from(context)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ExploreInnerViewHolder(
            ExploreInnerViewHolder.createView(layoutInflater, parent),
            onItemClickListener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { label ->
            holder as ExploreInnerViewHolder
            holder.bind(label)
        }
    }
}