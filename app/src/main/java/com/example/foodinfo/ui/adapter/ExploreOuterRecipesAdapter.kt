package com.example.foodinfo.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.foodinfo.R
import com.example.foodinfo.model.entities.RecipeCategoryLabelItem
import com.example.foodinfo.ui.decorator.ExploreInnerItemDecoration

class ExploreOuterRecipesAdapter(
    private val context: Context,
    private val onOuterItemClickListener: (String) -> Unit,
    private val onInnerItemClickListener: (String) -> Unit
) : ListAdapter<RecipeCategoryLabelItem, ExploreOuterRecipesAdapter.SearchViewHolder>(
    AsyncDifferConfig.Builder(RecipeCategoryLabelItem.ItemCallBack).build()
) {

    private val viewPool = RecyclerView.RecycledViewPool()
    private val layoutInflater = LayoutInflater.from(context)


    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val labelTitle: TextView = itemView.findViewById(R.id.tv_search_item_header)
        val recyclerView: RecyclerView = itemView.findViewById(R.id.rv_explore_outer_item)
        val expandHeader: ConstraintLayout = itemView.findViewById(R.id.cl_expand_header)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val itemView = layoutInflater.inflate(
            R.layout.explore_rv_outer_item, parent, false
        )
        val holder = SearchViewHolder(itemView)

        holder.recyclerView.addItemDecoration(
            ExploreInnerItemDecoration(
                context.resources.getDimensionPixelSize(R.dimen.search_recipes_space),
                context.resources.getDimensionPixelSize(R.dimen.activity_horizontal_margin),
            )
        )

        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL

        // оптимизация скроллинга внешнего RecyclerView
        holder.recyclerView.setRecycledViewPool(viewPool)
        layoutManager.recycleChildrenOnDetach = true

        holder.recyclerView.layoutManager = layoutManager

        return holder
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.labelTitle.text = getItem(position).label
        val recyclerAdapter =
            ExploreInnerRecipesAdapter(context, onInnerItemClickListener)
        recyclerAdapter.submitList(getItem(position).recipes)
        holder.recyclerView.adapter = recyclerAdapter
        holder.expandHeader.setOnClickListener {
            onOuterItemClickListener(getItem(position).label)
        }
    }
}