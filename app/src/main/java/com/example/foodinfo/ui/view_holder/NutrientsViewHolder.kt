package com.example.foodinfo.ui.view_holder

import com.example.foodinfo.databinding.RvItemRecipeNutrientBinding
import com.example.foodinfo.repository.model.NutrientRecipeModel


class NutrientsViewHolder(
    private val binding: RvItemRecipeNutrientBinding,
    private val onGetNutrientWeight: (Float, Float, String) -> String,
    private val onGetNutrientPercent: (Int) -> String,
    private val onNutrientClickListener: (String) -> Unit,
) : BaseViewHolder<RvItemRecipeNutrientBinding, NutrientRecipeModel>(binding) {

    init {
        binding.clNutrient.setOnClickListener { onNutrientClickListener(item.fieldInfo.name) }
    }


    override fun bind(newItem: NutrientRecipeModel) {
        super.bind(newItem)
        binding.tvName.text = item.fieldInfo.name
        binding.tvWeight.text = onGetNutrientWeight.invoke(
            item.totalWeight,
            item.fieldInfo.dailyAllowance,
            item.fieldInfo.measure
        )
        binding.tvPercent.text = onGetNutrientPercent.invoke(item.dailyPercent)
        binding.progressBar.progress = item.dailyPercent
    }
}