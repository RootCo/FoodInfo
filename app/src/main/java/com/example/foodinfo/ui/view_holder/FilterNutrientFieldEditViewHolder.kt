package com.example.foodinfo.ui.view_holder

import com.example.foodinfo.databinding.RvItemFilterInputNutrientsEditBinding
import com.example.foodinfo.repository.model.NutrientFilterEditModel


class FilterNutrientFieldEditViewHolder(
    private val binding: RvItemFilterInputNutrientsEditBinding,
    onHeaderClickCallback: (String) -> Unit,
    onValueChangedCallback: (Float, Float) -> Unit
) : BaseViewHolder<RvItemFilterInputNutrientsEditBinding, NutrientFilterEditModel>(binding) {

    private val onValueChangedCallback: (Float, Float) -> Unit = { minValue, maxValue ->
        item.minValue = minValue
        item.maxValue = maxValue
    }


    init {
        binding.root.addStopTrackingCallback(this.onValueChangedCallback)
        binding.root.addStopTrackingCallback(onValueChangedCallback)
        binding.root.addHeaderClickCallback(onHeaderClickCallback)
    }


    override fun bind(newItem: NutrientFilterEditModel) {
        super.bind(newItem)
        with(binding.root) {
            header = item.name
            measure = item.measure
            stepSize = item.stepSize
            rangeMin = item.rangeMin
            rangeMax = item.rangeMax
            minValue = item.minValue
            maxValue = item.maxValue
        }
    }
}