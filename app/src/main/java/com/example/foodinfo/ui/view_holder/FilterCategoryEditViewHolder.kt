package com.example.foodinfo.ui.view_holder

import com.example.foodinfo.databinding.RvItemFilterInputCategoryEditBinding
import com.example.foodinfo.repository.model.LabelFilterEditModel


class FilterCategoryEditViewHolder(
    private val binding: RvItemFilterInputCategoryEditBinding,
    private val onQuestionMarkClickListener: (String) -> Unit,
) : BaseViewHolder<RvItemFilterInputCategoryEditBinding, LabelFilterEditModel>(binding) {

    init {
        binding.ivQuestionMark.setOnClickListener { onQuestionMarkClickListener(item.name) }
        binding.tvHeader.setOnClickListener {
            item.isSelected = !item.isSelected
            binding.cbChecked.isChecked = item.isSelected
        }
    }

    override fun bind(newItem: LabelFilterEditModel) {
        super.bind(newItem)
        with(binding) {
            tvHeader.text = item.name
            cbChecked.isChecked = item.isSelected
        }
    }
}