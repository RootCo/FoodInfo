package com.example.foodinfo.ui.view_holder

import com.example.foodinfo.databinding.RvItemFilterInputNutrientsEditBinding
import com.example.foodinfo.repository.model.RangeFieldModel


class FilterNutrientFieldEditViewHolder(
    private val binding: RvItemFilterInputNutrientsEditBinding,
    onHeaderClickCallback: (String) -> Unit,
    onValueChangedCallback: (Float, Float, Boolean) -> Unit
) : BaseViewHolder<RvItemFilterInputNutrientsEditBinding, RangeFieldModel>(binding) {

    private val onValueChangedCallback: (Float, Float, Boolean) -> Unit =
        { minValue, maxValue, _ ->
            item.minCurrent = minValue
            item.maxCurrent = maxValue
        }


    init {
        binding.root.addStopTrackingCallback(this.onValueChangedCallback)
        binding.root.addStopTrackingCallback(onValueChangedCallback)
        binding.root.addHeaderClickCallback(onHeaderClickCallback)
    }


    override fun bind(newItem: RangeFieldModel) {
        super.bind(newItem)
        with(binding.root) {
            questionMarkVisible = true
            header = item.name
            measure = item.measure
            stepSize = item.stepSize
            minValue = item.minValue
            maxValue = item.maxValue
            minCurrent = item.minCurrent
            maxCurrent = item.maxCurrent
        }
    }
}