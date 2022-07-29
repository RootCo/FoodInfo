package com.example.foodinfo.ui.view_holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.foodinfo.R
import com.example.foodinfo.model.local.RecipeExplore
import com.google.android.material.imageview.ShapeableImageView


class HomeViewHolder(itemView: View, onItemClickListener: (String) -> Unit) :
    BaseViewHolder<RecipeExplore>(itemView) {

    private val caloriesView: TextView = itemView.findViewById(
        R.id.tv_home_rv_recipe_calories
    )
    private val nameView: TextView = itemView.findViewById(
        R.id.tv_home_rv_recipe_name
    )
    private val imageView: ShapeableImageView = itemView.findViewById(
        R.id.iv_home_rv_recipe_preview
    )


    init {
        imageView.setOnClickListener {
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
                R.layout.rv_item_home, parent, false
            )
        }
    }
}