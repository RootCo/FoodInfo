package com.example.foodinfo.ui.view_holder

import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.foodinfo.databinding.RvItemExploreInnerBinding
import com.example.foodinfo.repository.model.LabelModel


class ExploreInnerViewHolder(
    private val binding: RvItemExploreInnerBinding,
    onItemClickListener: (String, String) -> Unit
) : BaseViewHolder<RvItemExploreInnerBinding, LabelModel>(binding) {

    init {
        binding.clExploreInnerItem.setOnClickListener {
            onItemClickListener(item.category, item.label)
        }
    }


    override fun bind(newItem: LabelModel): Unit = with(binding) {
        super.bind(newItem)
        tvTitle.text = item.label
        Glide.with(icPreview.context)
            .load(item.previewURL)
            .placeholder(null)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(icPreview)
    }
}
