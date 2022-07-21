package com.example.foodinfo.ui.view_holder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodinfo.R
import com.example.foodinfo.model.local.CategoryItem
import com.example.foodinfo.ui.adapter.ExploreInnerRecipesAdapter
import com.example.foodinfo.ui.decorator.ExploreInnerItemDecoration


class ExploreOuterViewHolder(
    itemView: View,
    context: Context,
    onInnerItemClickListener: (String) -> Unit,
    onOuterItemClickListener: (String, String) -> Unit,
    private val onReadyToLoadData: (
        RecyclerView, ExploreInnerRecipesAdapter, CategoryItem
    ) -> Unit
) :
    BaseViewHolder<CategoryItem>(itemView) {

    private val labelTitle: TextView = itemView.findViewById(
        R.id.tv_search_item_header
    )
    private val recyclerView: RecyclerView = itemView.findViewById(
        R.id.rv_explore_outer_item
    )
    private val expandHeader: FrameLayout = itemView.findViewById(
        R.id.ll_expand_header
    )
    private val recipesProgress: ProgressBar = itemView.findViewById(
        R.id.explore_progress
    )
    private val recyclerAdapter: ExploreInnerRecipesAdapter

    init {
        expandHeader.setOnClickListener {
            onOuterItemClickListener(item.category, item.label)
        }

        val layoutManager = LinearLayoutManager(context).also {
            it.initialPrefetchItemCount = 4
            it.orientation = LinearLayoutManager.HORIZONTAL
        }

        recyclerAdapter = ExploreInnerRecipesAdapter(context, onInnerItemClickListener)
        recyclerAdapter.addLoadStateListener { state: CombinedLoadStates ->
            recyclerView.isVisible = state.refresh != LoadState.Loading
            recipesProgress.isVisible = state.refresh == LoadState.Loading
        }

        recyclerView.addItemDecoration(
            ExploreInnerItemDecoration(
                context.resources.getDimensionPixelSize(R.dimen.search_recipes_space),
                context.resources.getDimensionPixelSize(R.dimen.activity_horizontal_margin),
            )
        )
        recyclerView.adapter = recyclerAdapter
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = layoutManager
    }


    override fun bind(item: CategoryItem) {
        super.bind(item)
        with(this.item) {
            labelTitle.text = label
            onReadyToLoadData.invoke(recyclerView, recyclerAdapter, this)
        }
    }

    fun saveState() {
        item.state = recyclerView.layoutManager?.onSaveInstanceState() ?: return
    }

    companion object {
        fun createView(layoutInflater: LayoutInflater, parent: ViewGroup): View {
            return layoutInflater.inflate(
                R.layout.explore_rv_outer_item, parent, false
            )
        }
    }
}
