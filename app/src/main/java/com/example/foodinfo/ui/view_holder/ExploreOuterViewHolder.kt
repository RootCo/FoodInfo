package com.example.foodinfo.ui.view_holder

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodinfo.R
import com.example.foodinfo.databinding.RvItemExploreOuterBinding
import com.example.foodinfo.repository.model.Category
import com.example.foodinfo.ui.adapter.ExploreInnerAdapter
import com.example.foodinfo.ui.decorator.ExploreInnerItemDecoration
import com.example.foodinfo.utils.getState
import com.example.foodinfo.utils.restoreState


class ExploreOuterViewHolder(
    private val binding: RvItemExploreOuterBinding,
    context: Context,
    onItemClickListener: (String, String) -> Unit
) : BaseViewHolder<RvItemExploreOuterBinding, Category>(binding) {

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

        with(binding.rvExploreOuterItem) {
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

    override fun bind(newItem: Category): Unit = with(binding) {
        super.bind(newItem)
        tvTitle.text = item.category
    }

    fun subscribe(): Unit = with(binding) {
        recyclerAdapter.submitList(item.labels)
        rvExploreOuterItem.restoreState(item.state)
    }

    fun saveState(): Unit = with(binding) {
        item.state = rvExploreOuterItem.getState()
    }
}
