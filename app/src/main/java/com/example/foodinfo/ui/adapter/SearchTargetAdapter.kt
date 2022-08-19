package com.example.foodinfo.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.foodinfo.databinding.RvItemSearchTargetBinding
import com.example.foodinfo.databinding.RvItemSearchTargetPlaceholderBinding
import com.example.foodinfo.repository.model.RecipeShortModel
import com.example.foodinfo.ui.view_holder.SearchTargetPlaceholder
import com.example.foodinfo.ui.view_holder.SearchTargetViewHolder


class SearchTargetAdapter(
    context: Context,
    private val onGetTime: (Int) -> String,
    private val onItemClickListener: (String) -> Unit
) : PagingDataAdapter<RecipeShortModel, ViewHolder>(
    RecipeShortModel.ItemCallBack
) {

    private val layoutInflater = LayoutInflater.from(context)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            ViewTypes.LOADED_VIEW.ordinal -> {
                SearchTargetViewHolder(
                    RvItemSearchTargetBinding.inflate(layoutInflater, parent, false),
                    onGetTime,
                    onItemClickListener
                )
            }
            else                          -> {
                SearchTargetPlaceholder(
                    RvItemSearchTargetPlaceholderBinding.inflate(
                        layoutInflater,
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { recipe ->
            holder as SearchTargetViewHolder
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