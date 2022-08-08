package com.example.foodinfo.ui.view_holder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.foodinfo.databinding.RvItemExploreInnerBinding
import com.example.foodinfo.model.local.CategoryLabel


class ExploreInnerViewHolder(
    private val binding: RvItemExploreInnerBinding,
    onItemClickListener: (String, String) -> Unit
) : BaseViewHolder<RvItemExploreInnerBinding, CategoryLabel>(binding) {

    init {
        binding.clExploreInnerItem.setOnClickListener {
            onItemClickListener(item.category, item.label)
        }
    }


    override fun bind(newItem: CategoryLabel): Unit = with(binding) {
        super.bind(newItem)
        tvTitle.text = item.label
        icPreview.setImageDrawable(item.icon)
    }

    companion object {
        fun createView(
            layoutInflater: LayoutInflater,
            parent: ViewGroup
        ): RvItemExploreInnerBinding {
            return RvItemExploreInnerBinding.inflate(layoutInflater, parent, false)
        }
    }
}
