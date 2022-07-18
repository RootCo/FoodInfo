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


class HomeViewHolder(
    itemView: View, utils: DecorationUtils, onItemClickListener: (String) -> Unit,
) : BaseViewHolder<RecipeExplore>(itemView) {

    val caloriesView: TextView = itemView.findViewById(
        R.id.tv_home_rv_recipe_calories
    )
    val nameView: TextView = itemView.findViewById(
        R.id.tv_home_rv_recipe_name
    )
    val imageView: ShapeableImageView = itemView.findViewById(
        R.id.iv_home_rv_recipe_preview
    )

    init {
        itemView.layoutParams = ViewGroup.LayoutParams(
            (utils.homeRecipesWidth), ViewGroup.LayoutParams.MATCH_PARENT
        )
        imageView.setOnClickListener { onItemClickListener(item.id) }
    }

    override fun bind(item: RecipeExplore) {
        super.bind(item)
        with(item) {
            caloriesView.text = calories.toString()
            nameView.text = name
            Glide.with(imageView.context).load(preview).into(imageView)
        }
    }

    companion object {
        fun createView(layoutInflater: LayoutInflater, parent: ViewGroup): View {
            return layoutInflater.inflate(
                R.layout.home_rv_item, parent, false
            )
        }
    }
}