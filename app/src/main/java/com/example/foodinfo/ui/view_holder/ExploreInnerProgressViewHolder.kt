package com.example.foodinfo.ui.view_holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.foodinfo.R
import com.example.foodinfo.utils.DecorationUtils


class ExploreInnerProgressViewHolder(itemView: View, utils: DecorationUtils) :
    BaseViewHolder<Any>(itemView) {

    init {
        itemView.layoutParams = ViewGroup.LayoutParams(
            utils.exploreItemWidth, utils.exploreItemHeight
        )
    }

    companion object {
        fun createView(layoutInflater: LayoutInflater, parent: ViewGroup): View {
            return layoutInflater.inflate(
                R.layout.rv_item_explore_inner_placeholder, parent, false
            )
        }
    }
}