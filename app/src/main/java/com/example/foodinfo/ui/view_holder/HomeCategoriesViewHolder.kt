package com.example.foodinfo.ui.view_holder

import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.foodinfo.R
import com.example.foodinfo.databinding.RvItemCategoryBinding
import com.example.foodinfo.repository.model.CategoryModel
import com.example.foodinfo.repository.model.LabelModel
import com.example.foodinfo.utils.glide.GlideApp


class HomeCategoriesViewHolder(
    private val binding: RvItemCategoryBinding,
    onItemClickListener: (String) -> Unit
) : BaseViewHolder<RvItemCategoryBinding, CategoryModel>(binding) {

    init {
        binding.clExploreInnerItem.setOnClickListener {
            onItemClickListener(item.name)
        }
    }


    override fun bind(newItem: CategoryModel) {
        super.bind(newItem)
        binding.tvTitle.text = item.name
        GlideApp.with(binding.icPreview.context)
            .load(item.preview)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .error(R.drawable.ic_no_image)
            .into(binding.icPreview)
    }
}