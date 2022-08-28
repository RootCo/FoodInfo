package com.example.foodinfo.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.foodinfo.databinding.RvItemSearchTargetBinding
import com.example.foodinfo.databinding.RvItemSearchTargetPlaceholderBinding
import com.example.foodinfo.repository.model.RecipeShortModel
import com.example.foodinfo.ui.view_holder.SearchRecipePlaceholder
import com.example.foodinfo.ui.view_holder.SearchRecipeViewHolder


class SearchRecipeAdapter(
    context: Context,
    private val onGetTime: (Int) -> String,
    private val onItemClickListener: (String) -> Unit,
    private val onFavoriteClickListener: (String) -> Unit,
) : PagingDataAdapter<RecipeShortModel, ViewHolder>(
    RecipeShortModel.ItemCallBack
) {

    private val layoutInflater = LayoutInflater.from(context)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            ViewTypes.LOADED_VIEW.ordinal -> {
                SearchRecipeViewHolder(
                    RvItemSearchTargetBinding.inflate(layoutInflater, parent, false),
                    onGetTime,
                    onItemClickListener,
                    onFavoriteClickListener
                )
            }
            else                          -> {
                SearchRecipePlaceholder(
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
            holder as SearchRecipeViewHolder
            holder.bind(recipe)
        }
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            getItem(position)?.let { recipe ->
                holder as SearchRecipeViewHolder
                holder.bind(recipe, payloads)
            }
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