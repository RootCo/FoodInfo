package com.example.foodinfo.ui.view_holder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.foodinfo.databinding.RvItemHomeBinding
import com.example.foodinfo.model.local.RecipeExplore


class HomeViewHolder(
    private val binding: RvItemHomeBinding,
    onItemClickListener: (String) -> Unit
) : BaseViewHolder<RvItemHomeBinding, RecipeExplore>(binding) {

    init {
        binding.ivRecipePreview.setOnClickListener {
            onItemClickListener(item.id)
        }
    }


    override fun bind(newItem: RecipeExplore): Unit = with(binding) {
        super.bind(newItem)
        tvRecipeName.text = item.name
        tvCaloriesValue.text = item.calories.toString()
        Glide.with(ivRecipePreview.context)
            .load(item.preview)
            .into(ivRecipePreview)
    }


    companion object {
        fun createView(
            layoutInflater: LayoutInflater,
            parent: ViewGroup
        ): RvItemHomeBinding {
            return RvItemHomeBinding.inflate(layoutInflater, parent, false)
        }
    }
}