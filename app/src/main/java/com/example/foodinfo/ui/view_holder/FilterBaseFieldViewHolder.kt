package com.example.foodinfo.ui.view_holder

import com.example.foodinfo.databinding.RvItemFilterInputBaseFieldBinding
import com.example.foodinfo.repository.model.BaseFieldFilterEditModel


class FilterBaseFieldViewHolder(
    private val binding: RvItemFilterInputBaseFieldBinding,
    onValueChangedCallback: (Float, Float) -> Unit
) : BaseViewHolder<RvItemFilterInputBaseFieldBinding, BaseFieldFilterEditModel>(binding) {

    private val onValueChangedCallback: (Float, Float) -> Unit = { minValue, maxValue ->
        item.minValue = minValue
        item.maxValue = maxValue
    }


    init {
        binding.root.addStopTrackingCallback(this.onValueChangedCallback)
        binding.root.addStopTrackingCallback(onValueChangedCallback)
    }


    override fun bind(newItem: BaseFieldFilterEditModel) {
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