package com.example.foodinfo.utils

import android.content.Context
import com.example.foodinfo.R


class DecorationUtils(context: Context) {
    private val screenWidth: Int

    val homeRecipesWidth: Int

    init {
        with(context.resources) {
            screenWidth = displayMetrics.widthPixels
            homeRecipesWidth =
                screenWidth - getDimension(R.dimen.home_recipes_margin).toInt() * 2
        }
    }
}
