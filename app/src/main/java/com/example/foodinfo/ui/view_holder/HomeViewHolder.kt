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
        binding.ivHomeRvRecipePreview.setOnClickListener {
            onItemClickListener(item.id)
        }
    }


    override fun bind(newItem: RecipeExplore): Unit = with(binding) {
        super.bind(newItem)
        tvHomeRvRecipeName.text = item.name
        tvHomeRvRecipeCalories.text = item.calories.toString()
        Glide.with(ivHomeRvRecipePreview.context)
            .load(item.preview)
            .into(ivHomeRvRecipePreview)
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