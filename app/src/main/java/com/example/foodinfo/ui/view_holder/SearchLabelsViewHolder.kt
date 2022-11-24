package com.example.foodinfo.ui.view_holder

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.foodinfo.R
import com.example.foodinfo.databinding.RvItemLabelBinding
import com.example.foodinfo.repository.model.LabelModel
import com.example.foodinfo.utils.glide.GlideApp


class SearchLabelsViewHolder(
    private val binding: RvItemLabelBinding,
    onItemClickListener: (String, String) -> Unit,
) : BaseViewHolder<RvItemLabelBinding, LabelModel>(binding) {

    init {
        binding.ivPreview.setOnClickListener {
            onItemClickListener(item.category, item.label)
        }
    }


    override fun bind(newItem: LabelModel) {
        super.bind(newItem)
        binding.tvTitle.text = item.label
        GlideApp.with(binding.ivPreview.context)
            .load(item.preview)
            .error(R.drawable.ic_no_image)
            .placeholder(null)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(binding.ivPreview)
    }
}