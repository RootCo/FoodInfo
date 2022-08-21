package com.example.foodinfo.ui.decorator

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView


class SearchTargetItemDecoration(
    private val horizontal: Int, private val vertical: Int, private val columnsCount: Int
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