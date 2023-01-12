package com.example.foodinfo.ui.view_holder

import com.example.foodinfo.databinding.RvItemFilterInputBaseFieldBinding
import com.example.foodinfo.repository.model.BaseFieldFilterEditModel


class FilterBaseFieldViewHolder(
    private val binding: RvItemFilterInputBaseFieldBinding,
    onValueChangedCallback: (Long, Float, Float) -> Unit
) : BaseViewHolder<RvItemFilterInputBaseFieldBinding, BaseFieldFilterEditModel>(binding) {

    private val onValueChangedCallback: (Float, Float) -> Unit = { minValue, maxValue ->
        if (item.minValue != minValue || item.maxValue != maxValue) {
            onValueChangedCallback.invoke(item.id, minValue, maxValue)
        }
    }

    init {
        binding.root.addStopTrackingCallback(this.onValueChangedCallback)
    }


    override fun bind(newItem: BaseFieldFilterEditModel) {
        super.bind(newItem)
        with(binding.root) {
            item.apply {
                header = fieldInfo.name
                measure = fieldInfo.measure
                rangeMin = fieldInfo.rangeMin
                rangeMax = fieldInfo.rangeMax
                stepSize = fieldInfo.stepSize
                maxValue = maxValue
                minValue = minValue
            }
        }
    }
}