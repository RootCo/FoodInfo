package com.example.foodinfo.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.foodinfo.model.local.CategoryItem
import com.example.foodinfo.model.local.RecipeExplore
import com.example.foodinfo.ui.view_holder.ExploreOuterViewHolder
import kotlinx.coroutines.flow.StateFlow


class ExploreOuterRecipesAdapter(
    private val context: Context,
    private val onInnerItemClickListener: (String) -> Unit,
    private val onOuterItemClickListener: (String, String) -> Unit,
    private val onReadyToLoadData: (
        RecyclerView, ExploreInnerRecipesAdapter, CategoryItem
    ) -> Unit,
) : PagingDataAdapter<CategoryItem, ViewHolder>(
    CategoryItem.ItemCallBack
) {

    private val layoutInflater = LayoutInflater.from(context)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ExploreOuterViewHolder(
            ExploreOuterViewHolder.createView(layoutInflater, parent),
            context,
            onInnerItemClickListener,
            onOuterItemClickListener,
            onReadyToLoadData
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { recipes ->
            holder as ExploreOuterViewHolder
            holder.bind(recipes)
        }
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder as ExploreOuterViewHolder
        holder.saveState()
    }
}