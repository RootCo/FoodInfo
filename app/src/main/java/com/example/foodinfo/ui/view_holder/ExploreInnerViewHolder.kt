package com.example.foodinfo.ui.view_holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.foodinfo.R
import com.example.foodinfo.model.local.RecipeExplore
import com.example.foodinfo.utils.DecorationUtils
import com.google.android.material.imageview.ShapeableImageView


class ExploreInnerViewHolder(
    itemView: View, utils: DecorationUtils, onItemClickListener: (String) -> Unit,
) : BaseViewHolder<RecipeExplore>(itemView) {

    private val nameView: TextView = itemView.findViewById(
        R.id.tv_search_rv_recipe_name
    )
    private val caloriesView: TextView = itemView.findViewById(
        R.id.tv_search_rv_recipe_calories
    )
    private val imageView: ShapeableImageView = itemView.findViewById(
        R.id.iv_search_rv_recipe_preview
    )


    init {
        itemView.layoutParams = ViewGroup.LayoutParams(
            utils.exploreItemWidth, utils.exploreItemHeight
        )

        itemView.setOnClickListener {
            onItemClickListener(item.id)
        }
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
                R.layout.rv_item_explore_inner, parent, false
            )
        }
    }
}