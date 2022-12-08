package com.example.foodinfo.ui.view_holder

import android.util.Log
import com.example.foodinfo.databinding.RvItemFilterInputBaseFieldBinding
import com.example.foodinfo.repository.model.RangeFieldModel


class FilterBaseFieldViewHolder(
    private val binding: RvItemFilterInputBaseFieldBinding,
    onValueChangedCallback: (Float, Float, Boolean) -> Unit
) : BaseViewHolder<RvItemFilterInputBaseFieldBinding, RangeFieldModel>(binding) {

    init {
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
        }
    }
}