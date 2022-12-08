package com.example.foodinfo.ui.view_holder

import android.view.LayoutInflater
import com.example.foodinfo.databinding.ItemRecipeCategoryBinding
import com.example.foodinfo.repository.model.CategoryLabelsModel


class FilterInputCategoryViewHolder(
    private val inflater: LayoutInflater,
    private val binding: ItemRecipeCategoryBinding,
    private val onLabelClickListener: (String, String) -> Unit
) : BaseViewHolder<ItemRecipeCategoryBinding, CategoryLabelsModel>(binding) {

}