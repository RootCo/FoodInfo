package com.example.foodinfo.ui.view_holder

import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.foodinfo.R
import com.example.foodinfo.databinding.RvItemCategoryBinding
import com.example.foodinfo.repository.model.CategoryFieldModel
import com.example.foodinfo.utils.glide.GlideApp


class HomeCategoriesViewHolder(
    private val binding: RvItemCategoryBinding,
    onItemClickListener: (String) -> Unit
) : BaseViewHolder<RvItemCategoryBinding, CategoryFieldModel>(binding) {

    init {
        binding.clContent.setOnClickListener {
            onItemClickListener(item.name)
        }
    }


    override fun bind(newItem: CategoryFieldModel) {
        super.bind(newItem)
        binding.tvTitle.text = item.name
        GlideApp.with(binding.ivPreview.context)
            .load(item.preview)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .error(R.drawable.ic_no_image)
            .into(binding.ivPreview)
    }
}