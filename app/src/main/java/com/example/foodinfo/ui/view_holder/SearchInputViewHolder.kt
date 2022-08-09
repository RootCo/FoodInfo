package com.example.foodinfo.ui.view_holder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.foodinfo.databinding.RvItemSearchInputBinding
import com.example.foodinfo.local.model.SearchInput


class SearchInputViewHolder(
    private val binding: RvItemSearchInputBinding,
    onArrowClickListener: (String) -> Unit,
    onItemClickListener: (String) -> Unit
) : BaseViewHolder<RvItemSearchInputBinding, SearchInput>(binding) {

    init {
        with(binding) {
            tvSearchInput.setOnClickListener { onItemClickListener(item.inputText) }
            ivApplySearch.setOnClickListener { onItemClickListener(item.inputText) }
            ivApplyText.setOnClickListener { onArrowClickListener(item.inputText) }
        }
    }


    override fun bind(newItem: SearchInput): Unit = with(binding) {
        super.bind(newItem)
        tvSearchInput.text = item.inputText
    }


    companion object {
        fun createView(
            layoutInflater: LayoutInflater,
            parent: ViewGroup
        ): RvItemSearchInputBinding {
            return RvItemSearchInputBinding.inflate(layoutInflater, parent, false)
        }
    }
}