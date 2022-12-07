package com.example.foodinfo.ui.decorator

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView


class GridItemDecoration(
    private val horizontal: Int = 0,
    private val vertical: Int = 0,
    private val columnsCount: Int = 2
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        val column = parent.getChildAdapterPosition(view) % columnsCount
        with(outRect) {
            left = column * horizontal / columnsCount
            right = horizontal - (column + 1) * horizontal / columnsCount
            top = vertical
            bottom = vertical
        }
    }
}