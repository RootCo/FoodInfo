package com.example.foodinfo.ui.view_holder

import com.example.foodinfo.databinding.RvItemRecipeNutrientBinding
import com.example.foodinfo.repository.model.RecipeNutrientModel


class NutrientsViewHolder(
    private val binding: RvItemRecipeNutrientBinding,
    private val onGetNutrientWeight: (Double, Double, String) -> String,
    private val onGetNutrientPercent: (Int) -> String,
    private val onNutrientClickListener: (String) -> Unit,
) : BaseViewHolder<RvItemRecipeNutrientBinding, RecipeNutrientModel>(binding) {

    init {
        binding.clNutrient.setOnClickListener { onNutrientClickListener(item.label) }
    }


    override fun bind(newItem: RecipeNutrientModel) {
        super.bind(newItem)
        binding.tvName.text = item.label
        binding.tvWeight.text = onGetNutrientWeight.invoke(
            item.totalWeight,
            item.dailyWeight,
            item.measure
        )
        binding.tvPercent.text = onGetNutrientPercent.invoke(item.dailyPercent)
        binding.progressBar.progress = item.dailyPercent
    }
}