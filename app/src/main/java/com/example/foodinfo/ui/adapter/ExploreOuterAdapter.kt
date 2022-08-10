package com.example.foodinfo.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.foodinfo.repository.model.Category
import com.example.foodinfo.ui.view_holder.ExploreOuterViewHolder


class ExploreOuterAdapter(
    private val context: Context,
    private val onItemClickListener: (String, String) -> Unit
) : ListAdapter<Category, ViewHolder>(Category.ItemCallBack) {

    private val layoutInflater = LayoutInflater.from(context)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ExploreOuterViewHolder(
            ExploreOuterViewHolder.createView(layoutInflater, parent),
            context,
            onItemClickListener
        )
    }

    override fun onViewAttachedToWindow(holder: ViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder as ExploreOuterViewHolder
        holder.subscribe()
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder as ExploreOuterViewHolder
        holder.saveState()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { recipes ->
            holder as ExploreOuterViewHolder
            holder.bind(recipes)
        }
    }
}