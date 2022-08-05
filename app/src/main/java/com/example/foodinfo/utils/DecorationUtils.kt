package com.example.foodinfo.utils

import android.content.Context
import com.example.foodinfo.R


class DecorationUtils(context: Context) {
    val exploreItemWidth: Int
    val exploreItemHeight: Int

    init {
        with(context.resources) {
            val screenWidth = displayMetrics.widthPixels
            val exploreItemSpace = getDimension(R.dimen.explore_item_inner_space)
            val exploreItemMargin = getDimension(R.dimen.screen_horizontal_margin)
            val aspectRatio =
                getDimension(R.dimen.home_recipes_height) / getDimension(R.dimen.home_recipes_width)

            exploreItemWidth =
                ((screenWidth / 2) - exploreItemSpace - exploreItemMargin).toInt()
            exploreItemHeight = (exploreItemWidth * aspectRatio).toInt()
        }
    }
}
