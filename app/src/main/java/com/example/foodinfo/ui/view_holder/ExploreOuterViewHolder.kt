package com.example.foodinfo.ui.view_holder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodinfo.R
import com.example.foodinfo.model.local.CategoryItem
import com.example.foodinfo.ui.adapter.ExploreInnerAdapter
import com.example.foodinfo.ui.decorator.ExploreInnerItemDecoration
import com.example.foodinfo.utils.restoreState


class ExploreOuterViewHolder(
    itemView: View,
    context: Context,
    onItemClickListener: (String, String) -> Unit
) : BaseViewHolder<CategoryItem>(itemView) {

    private val title: TextView = itemView.findViewById(
        R.id.tv_title
    )
    private val recyclerView: RecyclerView = itemView.findViewById(
        R.id.rv_explore_outer_item
    )

    private val recyclerAdapter: ExploreInnerAdapter

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
    private val onScrollStateListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                saveState()
            }
        }
    }


    init {
        recyclerAdapter = ExploreInnerAdapter(context, onItemClickListener)

        with(recyclerView) {
            layoutManager = LinearLayoutManager(context).also {
                it.orientation = LinearLayoutManager.HORIZONTAL
            }
            adapter = recyclerAdapter
            setHasFixedSize(true)
            addItemDecoration(
                ExploreInnerItemDecoration(
                    context.resources.getDimensionPixelSize(R.dimen.explore_item_inner_space),
                    context.resources.getDimensionPixelSize(R.dimen.explore_item_inner_margin),
                )
            )
            addOnScrollListener(onScrollStateListener)
        }
    }

    override fun bind(item: CategoryItem) {
        super.bind(item)
        with(this.item) {
            title.text = category
        }
    }

    fun subscribe() {
        recyclerAdapter.submitList(item.labels)
        recyclerView.restoreState(item.state)
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
