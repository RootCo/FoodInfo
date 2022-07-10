package com.example.foodinfo.utils

import android.content.Context
import android.util.TypedValue

class Utils(context: Context) {
    private val screenWidth = context.resources.displayMetrics.widthPixels
    private val homeRecipesPaddingDP = 144f
    private val homeRecipesPaddingPX = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        homeRecipesPaddingDP,
        context.resources.displayMetrics
    ).toInt()

    /*
    отнимаем от ширины экрана 144dp это нужно для того, чтобы каждый элемент
    в RecyclerView был на 72dp меньше экрана с каждой стороны, НО отступ
    между элементами был другого размера
    для доп информации см HomeItemDecoration
     */
    fun getHomeRecipeWidth(): Int {
        return screenWidth - homeRecipesPaddingPX
    }
}