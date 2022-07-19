package com.example.foodinfo.ui.view_holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.foodinfo.R


class ExploreInnerProgressViewHolder(itemView: View) : BaseViewHolder<Any>(itemView) {
    companion object {
        fun createView(layoutInflater: LayoutInflater, parent: ViewGroup): View {
            return layoutInflater.inflate(
                R.layout.explore_rv_inner_item_placeholder, parent, false
            )
        }
    }
}