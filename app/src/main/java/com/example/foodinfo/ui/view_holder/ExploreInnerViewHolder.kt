package com.example.foodinfo.ui.view_holder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.foodinfo.databinding.RvItemExploreInnerBinding
import com.example.foodinfo.model.local.CategoryLabel
import com.example.foodinfo.utils.getDrawableByName


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
        Glide.with(icPreview.context)
            .load(icPreview.getDrawableByName(item.previewURL))
            .into(icPreview)
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
