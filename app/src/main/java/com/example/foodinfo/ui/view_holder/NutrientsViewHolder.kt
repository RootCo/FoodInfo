package com.example.foodinfo.ui.view_holder

import com.example.foodinfo.databinding.RvItemNutrientBinding
import com.example.foodinfo.repository.model.RecipeNutrientModel


class NutrientsViewHolder(
    private val binding: RvItemNutrientBinding,
    private val onGetNutrientWeight: (Double, Double, String) -> String,
    private val onGetNutrientPercent: (Int) -> String,
    private val onNutrientClickListener: (String) -> Unit,
) : BaseViewHolder<RvItemNutrientBinding, RecipeNutrientModel>(binding) {

    init {
        binding.clNutrient.setOnClickListener { onNutrientClickListener(item.label) }
    }


    override fun bind(newItem: RecipeNutrientModel): Unit = with(binding) {
        super.bind(newItem)
        tvName.text = item.label
        tvWeight.text = onGetNutrientWeight.invoke(
            item.totalWeight,
            item.dailyWeight,
            item.measure
        )
        tvPercent.text = onGetNutrientPercent.invoke(item.dailyPercent)
        progressBar.progress = item.dailyPercent
    }
}