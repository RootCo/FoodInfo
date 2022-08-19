package com.example.foodinfo.ui.decorator

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView


class SearchTargetItemDecoration(
    private val horizontal: Int, private val vertical: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        with(outRect) {
            top = vertical
            bottom = vertical
            right = horizontal
            left = horizontal
        }
    }
}