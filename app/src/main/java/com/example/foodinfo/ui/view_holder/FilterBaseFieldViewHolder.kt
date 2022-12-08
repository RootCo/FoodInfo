package com.example.foodinfo.ui.view_holder

import com.example.foodinfo.databinding.RvItemFilterInputBaseFieldBinding
import com.example.foodinfo.repository.model.RangeFieldModel


class FilterBaseFieldViewHolder(
    private val binding: RvItemFilterInputBaseFieldBinding,
    onValueChangedCallback: (Float, Float, Boolean) -> Unit
) : BaseViewHolder<RvItemFilterInputBaseFieldBinding, RangeFieldModel>(binding) {

    private val onValueChangedCallback: (Float, Float, Boolean) -> Unit =
        { minValue, maxValue, _ ->
            item.minCurrent = minValue
            item.maxCurrent = maxValue
        }


    init {
        binding.root.addStopTrackingCallback(this.onValueChangedCallback)
        binding.root.addStopTrackingCallback(onValueChangedCallback)
    }


    override fun bind(newItem: RangeFieldModel) {
        super.bind(newItem)
        with(binding.root) {
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