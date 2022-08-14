package com.example.foodinfo.ui.view_holder

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.foodinfo.R
import com.example.foodinfo.databinding.RvItemIngredientBinding
import com.example.foodinfo.repository.model.RecipeIngredientModel
import com.example.foodinfo.utils.glide.GlideApp


class IngredientsViewHolder(
    private val binding: RvItemIngredientBinding,
    private val onGetWeight: (Double) -> String,
    private val onGetQuantity: (Double, String) -> String,
) : BaseViewHolder<RvItemIngredientBinding, RecipeIngredientModel>(binding) {

    override fun bind(newItem: RecipeIngredientModel): Unit = with(binding) {
        super.bind(newItem)
        tvIngredientName.text = item.text
        tvIngredientValue.text = onGetQuantity.invoke(item.quantity, item.measure)
        tvIngredientWeight.text = onGetWeight.invoke(item.weight)
        GlideApp.with(ivIngredientPreview.context)
            .load(item.previewURL)
            .error(R.drawable.ic_no_image)
            .placeholder(null)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(ivIngredientPreview)
    }
}