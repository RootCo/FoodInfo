package com.example.foodinfo.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.foodinfo.model.local.RecipeCategoryLabelItem
import com.example.foodinfo.model.local.RecipeExplore
import com.example.foodinfo.ui.view_holder.ExploreOuterViewHolder
import kotlinx.coroutines.flow.Flow


class ExploreOuterRecipesAdapter(
    private val context: Context,
    private val onOuterItemClickListener: (String) -> Unit,
    private val onInnerItemClickListener: (String) -> Unit,
    private val onReadyToLoadData: (
        ExploreInnerRecipesAdapter,
        Flow<PagingData<RecipeExplore>>
    ) -> Unit,
) : PagingDataAdapter<RecipeCategoryLabelItem, ViewHolder>(
    RecipeCategoryLabelItem.ItemCallBack
) {

    private val viewPool = RecyclerView.RecycledViewPool()
    private val layoutInflater = LayoutInflater.from(context)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ExploreOuterViewHolder(
            ExploreOuterViewHolder.createView(layoutInflater, parent),
            context,
            viewPool,
            onOuterItemClickListener,
            onInnerItemClickListener,
            onReadyToLoadData
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { recipes ->
            holder as ExploreOuterViewHolder
            holder.bind(recipes)
        }
    }
}