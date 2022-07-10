package com.example.foodinfo.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodinfo.R
import com.example.foodinfo.model.entities.RecipeShort
import com.google.android.material.imageview.ShapeableImageView

class ExploreInnerRecipesAdapter(
    context: Context, private val onInnerItemClickListener: (String) -> Unit
) : RecyclerView.Adapter<ExploreInnerRecipesAdapter.SearchViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)

    private lateinit var data: List<RecipeShort>
    fun setContent(data: List<RecipeShort>) {
        this.data = data
        notifyDataSetChanged()
    }

    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameView: TextView = itemView.findViewById(R.id.tv_search_recipe_name)
        val caloriesView: TextView = itemView.findViewById(R.id.tv_search_recipe_calories)
        val imageView: ShapeableImageView =
            itemView.findViewById(R.id.iv_search_recipe_preview)
        val view = itemView
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): SearchViewHolder {
        val itemView = layoutInflater.inflate(
            R.layout.explore_rv_inner_item, parent, false
        )
        return SearchViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: SearchViewHolder, position: Int
    ) {
        holder.nameView.text = data[position].name
        holder.caloriesView.text = data[position].calories.toString()
        Glide.with(holder.imageView.context).load(data[position].preview)
            .into(holder.imageView)
        holder.view.setOnClickListener { onInnerItemClickListener(data[position].id) }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}