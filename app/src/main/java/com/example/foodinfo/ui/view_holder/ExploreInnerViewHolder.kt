package com.example.foodinfo.ui.view_holder

import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.foodinfo.R
import com.example.foodinfo.databinding.RvItemExploreInnerBinding
import com.example.foodinfo.repository.model.LabelModel
import com.example.foodinfo.utils.glide.GlideApp


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
        GlideApp.with(icPreview.context)
            .load(item.preview)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .error(R.drawable.ic_no_image)
            .into(icPreview)
    }
}