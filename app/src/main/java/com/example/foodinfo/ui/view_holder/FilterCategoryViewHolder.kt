package com.example.foodinfo.ui.view_holder

import android.view.LayoutInflater
import androidx.core.view.isVisible
import com.example.foodinfo.databinding.RvItemFilterInputCategoryBinding
import com.example.foodinfo.databinding.TvChipBinding
import com.example.foodinfo.repository.model.CategoryLabelsModel


class FilterCategoryViewHolder(
    private val inflater: LayoutInflater,
    private val binding: RvItemFilterInputCategoryBinding,
    private val onBtnEditClickListener: (String) -> Unit
) : BaseViewHolder<RvItemFilterInputCategoryBinding, CategoryLabelsModel>(binding) {

    init {
        binding.ivEdit.setOnClickListener { onBtnEditClickListener(item.name) }
    }

    override fun bind(newItem: CategoryLabelsModel) {
        super.bind(newItem)
        binding.tvTitle.text = item.name
        if (item.labels.size == 0) {
            binding.cgLabels.isVisible = false
            binding.tvUnspecified.isVisible = true
        } else {
            binding.cgLabels.isVisible = true
            binding.tvUnspecified.isVisible = false
            for (label in item.labels) {
                binding.cgLabels.addView(
                    TvChipBinding.inflate(inflater, null, false).apply {
                        this.textView.text = label
                    }.root
                )
            }
        }
    }
}