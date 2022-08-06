package com.example.foodinfo.ui.view_holder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.foodinfo.databinding.RvItemHomePlaceholderBinding


class HomeProgressViewHolder(binding: RvItemHomePlaceholderBinding) :
    BaseViewHolder<RvItemHomePlaceholderBinding, Any>(binding) {
    companion object {
        fun createView(
            layoutInflater: LayoutInflater,
            parent: ViewGroup
        ): RvItemHomePlaceholderBinding {
            return RvItemHomePlaceholderBinding.inflate(layoutInflater, parent, false)
        }
    }
}