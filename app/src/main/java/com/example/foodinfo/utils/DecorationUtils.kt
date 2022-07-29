package com.example.foodinfo.utils

import android.content.Context
import com.example.foodinfo.R


class DecorationUtils(context: Context) {
    val homeRecipesMargin: Int

    init {
        with(context.resources) {
            homeRecipesMargin =
                ((displayMetrics.widthPixels - getDimension(R.dimen.home_recipes_width)) / 2).toInt()
        }
    }
}
