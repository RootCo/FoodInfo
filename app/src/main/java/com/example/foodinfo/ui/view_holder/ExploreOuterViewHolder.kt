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
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodinfo.R
import com.example.foodinfo.model.local.RecipeCategoryLabelItem
import com.example.foodinfo.model.local.RecipeExplore
import com.example.foodinfo.ui.adapter.ExploreInnerRecipesAdapter
import com.example.foodinfo.ui.decorator.ExploreInnerItemDecoration
import kotlinx.coroutines.flow.Flow


class ExploreOuterViewHolder(
    itemView: View,
    context: Context,
    viewPool: RecyclerView.RecycledViewPool,
    onOuterItemClickListener: (String) -> Unit,
    onInnerItemClickListener: (String) -> Unit,
    private val onReadyToLoadData: (
        ExploreInnerRecipesAdapter,
        Flow<PagingData<RecipeExplore>>
    ) -> Unit
) :
    BaseViewHolder<RecipeCategoryLabelItem>(itemView) {

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
        expandHeader.setOnClickListener { onOuterItemClickListener(item.label) }

        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        layoutManager.recycleChildrenOnDetach = true

        recyclerView.addItemDecoration(
            ExploreInnerItemDecoration(
                context.resources.getDimensionPixelSize(R.dimen.search_recipes_space),
                context.resources.getDimensionPixelSize(R.dimen.activity_horizontal_margin),
            )
        )
        recyclerView.setRecycledViewPool(viewPool)
        recyclerView.layoutManager = layoutManager

        recyclerAdapter = ExploreInnerRecipesAdapter(context, onInnerItemClickListener)
        recyclerAdapter.addLoadStateListener { state: CombinedLoadStates ->
            recyclerView.isVisible = state.refresh != LoadState.Loading
            recipesProgress.isVisible = state.refresh == LoadState.Loading
        }
    }


    override fun bind(item: RecipeCategoryLabelItem) {
        super.bind(item)
        with(item) {
            labelTitle.text = label
            recyclerView.adapter = recyclerAdapter
            onReadyToLoadData.invoke(recyclerAdapter, recipes)
        }
    }


    companion object {
        fun createView(layoutInflater: LayoutInflater, parent: ViewGroup): View {
            return layoutInflater.inflate(
                R.layout.explore_rv_outer_item, parent, false
            )
        }
    }
}
