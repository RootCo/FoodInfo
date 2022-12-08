package com.example.foodinfo.ui.view_holder

import com.example.foodinfo.databinding.RvItemFilterInputCategoryEditBinding
import com.example.foodinfo.repository.model.LabelFilterEditModel


class FilterCategoryEditViewHolder(
    private val binding: RvItemFilterInputCategoryEditBinding,
    private val onQuestionMarkClickListener: (String) -> Unit,
) : BaseViewHolder<RvItemFilterInputCategoryEditBinding, LabelFilterEditModel>(binding) {

    init {
        binding.ivQuestionMark.setOnClickListener { onQuestionMarkClickListener(item.label) }
        binding.tvHeader.setOnClickListener {
            item.selected = !item.selected
            binding.cbChecked.isChecked = item.selected
        }
    }

    override fun bind(newItem: LabelFilterEditModel) {
        super.bind(newItem)
        with(binding) {
            tvHeader.text = item.label
            cbChecked.isChecked = item.selected
        }
    }
}