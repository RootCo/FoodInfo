package com.example.foodinfo.ui.view_holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.foodinfo.R
import com.example.foodinfo.utils.DecorationUtils


class HomeProgressViewHolder(
    itemView: View, utils: DecorationUtils
) : BaseViewHolder<Any>(itemView) {

    init {
        itemView.layoutParams = ViewGroup.LayoutParams(
            (utils.homeRecipesWidth), ViewGroup.LayoutParams.MATCH_PARENT
        )
    }

    companion object {
        fun createView(layoutInflater: LayoutInflater, parent: ViewGroup): View {
            return layoutInflater.inflate(
                R.layout.home_rv_item_placeholder, parent, false
            )
        }
    }
}