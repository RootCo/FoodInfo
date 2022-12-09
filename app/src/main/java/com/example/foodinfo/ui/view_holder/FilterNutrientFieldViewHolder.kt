package com.example.foodinfo.ui.view_holder

import com.example.foodinfo.databinding.RvItemFilterInputNutrientsBinding
import com.example.foodinfo.repository.model.FilterNutrientModel


class FilterNutrientFieldViewHolder(
    private val binding: RvItemFilterInputNutrientsBinding,
    private val getFormattedRange: (Float, Float, String) -> String
) : BaseViewHolder<RvItemFilterInputNutrientsBinding, FilterNutrientModel>(binding) {


    override fun bind(newItem: FilterNutrientModel) {
        super.bind(newItem)
        with(binding) {
            tvHeader.text = item.name
            tvRange.text = getFormattedRange(item.minValue, item.maxValue, item.measure)
        }
    }
}