package com.example.foodinfo.ui.view_holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.foodinfo.R
import com.example.foodinfo.model.local.RecipeExplore
import com.google.android.material.imageview.ShapeableImageView


class ExploreInnerViewHolder(
    itemView: View, onItemClickListener: (String) -> Unit,
) : BaseViewHolder<RecipeExplore>(itemView) {

    private val nameView: TextView = itemView.findViewById(
        R.id.tv_search_recipe_name
    )
    private val caloriesView: TextView = itemView.findViewById(
        R.id.tv_search_recipe_calories
    )
    private val imageView: ShapeableImageView = itemView.findViewById(
        R.id.iv_search_recipe_preview
    )


    init {
        itemView.setOnClickListener { onItemClickListener(item.id) }
    }


    override fun bind(item: RecipeExplore) {
        super.bind(item)
        with(item) {
            nameView.text = name
            caloriesView.text = calories.toString()
            Glide.with(imageView.context).load(preview).into(imageView)
        }
    }


    companion object {
        fun createView(layoutInflater: LayoutInflater, parent: ViewGroup): View {
            return layoutInflater.inflate(
                R.layout.explore_rv_inner_item, parent, false
            )
        }
    }
}