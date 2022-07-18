package com.example.foodinfo.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.foodinfo.model.local.RecipeExplore
import com.example.foodinfo.ui.view_holder.HomeProgressViewHolder
import com.example.foodinfo.ui.view_holder.HomeViewHolder
import com.example.foodinfo.utils.applicationComponent


class HomeRecipesAdapter(
    context: Context,
    private val onItemClickListener: (String) -> Unit
) : PagingDataAdapter<RecipeExplore, ViewHolder>(
    RecipeExplore.ItemCallBack
) {

    private val layoutInflater = LayoutInflater.from(context)
    private val utils = context.applicationComponent.decorationUtils


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            ViewTypes.LOADED_VIEW.ordinal -> {
                HomeViewHolder(
                    HomeViewHolder.createView(layoutInflater, parent),
                    utils,
                    onItemClickListener
                )
            }
            else                          -> {
                HomeProgressViewHolder(
                    HomeProgressViewHolder.createView(layoutInflater, parent),
                    utils
                )
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { recipe ->
            holder as HomeViewHolder
            holder.bind(recipe)
        }
    }

    override fun getItemViewType(position: Int): Int {
        getItem(position) ?: return ViewTypes.PROGRESS_VIEW.ordinal
        return ViewTypes.LOADED_VIEW.ordinal
    }

    enum class ViewTypes {
        PROGRESS_VIEW,
        LOADED_VIEW
    }
}