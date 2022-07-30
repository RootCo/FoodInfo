package com.example.foodinfo.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.foodinfo.model.local.CategoryItem
import com.example.foodinfo.ui.view_holder.ExploreOuterViewHolder
import com.example.foodinfo.utils.applicationComponent


class ExploreOuterRecipesAdapter(
    private val context: Context,
    private val onInnerItemClickListener: (String) -> Unit,
    private val onOuterItemClickListener: (String, String) -> Unit,
    private val readyToRestoreState: (
        ExploreInnerRecipesAdapter,
        CategoryItem,
        RecyclerView
    ) -> Unit,
    private val readyToSubscribe: (
        ExploreInnerRecipesAdapter,
        CategoryItem
    ) -> Unit,
) : PagingDataAdapter<CategoryItem, ViewHolder>(CategoryItem.ItemCallBack) {

    private val layoutInflater = LayoutInflater.from(context)
    private val utils = context.applicationComponent.decorationUtils


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ExploreOuterViewHolder(
            ExploreOuterViewHolder.createView(layoutInflater, parent),
            utils,
            context,
            onInnerItemClickListener,
            onOuterItemClickListener,
            readyToRestoreState,
            readyToSubscribe
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { recipes ->
            holder as ExploreOuterViewHolder
            holder.bind(recipes)
        }
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
}