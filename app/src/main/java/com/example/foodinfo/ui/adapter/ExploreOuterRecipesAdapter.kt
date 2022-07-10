package com.example.foodinfo.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodinfo.R
import com.example.foodinfo.model.entities.RecipeCategoryLabelItem
import com.example.foodinfo.ui.decorator.ExploreInnerItemDecoration

class ExploreOuterRecipesAdapter(
    private val context: Context,
    private val onOuterItemClickListener: (String) -> Unit,
    private val onInnerItemClickListener: (String) -> Unit
) : RecyclerView.Adapter<ExploreOuterRecipesAdapter.SearchViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)

    private lateinit var data: List<RecipeCategoryLabelItem>
    fun setContent(data: List<RecipeCategoryLabelItem>) {
        this.data = data
        notifyDataSetChanged()
    }

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
                context.resources.getDimensionPixelSize(R.dimen.search_recipes_margin)
            )
        )

        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        holder.recyclerView.layoutManager = layoutManager

        return holder
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.labelTitle.text = data[position].label
        val recyclerAdapter =
            ExploreInnerRecipesAdapter(context, onInnerItemClickListener)
        recyclerAdapter.setContent(data[position].recipes)
        holder.recyclerView.adapter = recyclerAdapter
        holder.expandHeader.setOnClickListener { onOuterItemClickListener(data[position].label) }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}