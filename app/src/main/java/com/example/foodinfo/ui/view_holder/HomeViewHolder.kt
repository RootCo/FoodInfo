package com.example.foodinfo.ui.view_holder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.foodinfo.R
import com.example.foodinfo.databinding.RvItemHomeBinding
import com.example.foodinfo.repository.model.RecipeShortModel


class HomeViewHolder(
    private val binding: RvItemHomeBinding,
    onItemClickListener: (String) -> Unit
) : BaseViewHolder<RvItemHomeBinding, RecipeShortModel>(binding) {

    init {
        binding.ivRecipePreview.setOnClickListener {
            onItemClickListener(item.id)
        }
    }


    override fun bind(newItem: RecipeShortModel): Unit = with(binding) {
        super.bind(newItem)
        tvRecipeName.text = item.name
        tvTimeValue.text = item.totalTime
        tvServingsValue.text = item.servings
        tvCaloriesValue.text = item.calories
        Glide.with(ivRecipePreview.context)
            .load(item.previewURL)
            .error(R.drawable.ic_image_placeholder)
            .placeholder(null)
            .transition(DrawableTransitionOptions.withCrossFade())
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