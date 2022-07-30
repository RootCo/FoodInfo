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
    private val readyToRestoreState: (
        ExploreInnerRecipesAdapter,
        CategoryItem,
        RecyclerView
    ) -> Unit,
    private val readyToSubscribe: (
        ExploreInnerRecipesAdapter,
        CategoryItem
    ) -> Unit,
) : BaseViewHolder<CategoryItem>(itemView) {


    private val labelTitle: TextView = itemView.findViewById(
        R.id.tv_search_item_header
    )
    val recyclerView: RecyclerView = itemView.findViewById(
        R.id.rv_explore_outer_item
    )
    private val expandHeader: FrameLayout = itemView.findViewById(
        R.id.ll_expand_header
    )
    private val progressBar: ProgressBar = itemView.findViewById(
        R.id.explore_progress
    )
    private val recyclerAdapter: ExploreInnerRecipesAdapter


    init {
        expandHeader.setOnClickListener {
            onOuterItemClickListener(item.category, item.label)
        }

        recyclerAdapter = ExploreInnerRecipesAdapter(context, onInnerItemClickListener)
        recyclerAdapter.addLoadStateListener { state: CombinedLoadStates ->
            recyclerView.isVisible = state.refresh != LoadState.Loading
            progressBar.isVisible = state.refresh == LoadState.Loading
        }

        recyclerView.layoutManager = LinearLayoutManager(context).also {
            it.initialPrefetchItemCount = 4
            it.orientation = LinearLayoutManager.HORIZONTAL
        }
        recyclerView.addItemDecoration(
            ExploreInnerItemDecoration(
                context.resources.getDimensionPixelSize(R.dimen.search_recipes_space),
                context.resources.getDimensionPixelSize(R.dimen.screen_horizontal_margin),
            )
        )

        /*
            For situations when user scrolls recycler and go to another fragment.
            In such situation onViewDetachedFromWindow() will not be called and scroll
            state will not be saved.
            One option to save state in that situation is to set
            recycleChildrenOnDetach = true for outerAdapter.layoutManager
            and save state in onViewRecycled() but recycleChildrenOnDetach isn't good
            for performance (as doc says) and can lead to undesired behavior in other
            situations
         */
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    saveState()
                }
            }
        })

        recyclerView.adapter = recyclerAdapter
        recyclerView.setHasFixedSize(true)
    }


    override fun bind(item: CategoryItem) {
        super.bind(item)
        with(this.item) {
            labelTitle.text = label
        }
    }

    fun subscribe() {
        readyToSubscribe.invoke(recyclerAdapter, item)
        readyToRestoreState.invoke(recyclerAdapter, item, recyclerView)
    }

    fun saveState() {
        item.state = recyclerView.layoutManager?.onSaveInstanceState() ?: return
    }

    companion object {
        fun createView(layoutInflater: LayoutInflater, parent: ViewGroup): View {
            return layoutInflater.inflate(
                R.layout.rv_item_explore_outer, parent, false
            )
        }
    }
}
