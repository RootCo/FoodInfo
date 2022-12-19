package com.example.foodinfo.ui.view_holder

import com.example.foodinfo.databinding.RvItemFilterInputNutrientsEditBinding
import com.example.foodinfo.repository.model.NutrientFilterEditModel


class FilterNutrientFieldEditViewHolder(
    private val binding: RvItemFilterInputNutrientsEditBinding,
    onHeaderClickCallback: (String) -> Unit,
    onValueChangedCallback: (Long, Float, Float) -> Unit
) : BaseViewHolder<RvItemFilterInputNutrientsEditBinding, NutrientFilterEditModel>(binding) {

    private val onValueChangedCallback: (Float, Float) -> Unit = { minValue, maxValue ->
        if (item.minValue != minValue || item.maxValue != maxValue) {
            onValueChangedCallback.invoke(item.id, minValue, maxValue)
        }
    }

    init {
        binding.root.addStopTrackingCallback(this.onValueChangedCallback)
        binding.root.addHeaderClickCallback(onHeaderClickCallback)
    }


    override fun bind(newItem: NutrientFilterEditModel) {
        super.bind(newItem)
        with(binding.root) {
            header = item.name
            measure = item.measure
            rangeMin = item.rangeMin
            rangeMax = item.rangeMax
            stepSize = item.stepSize
            maxValue = item.maxValue
            minValue = item.minValue
        }
    }
}