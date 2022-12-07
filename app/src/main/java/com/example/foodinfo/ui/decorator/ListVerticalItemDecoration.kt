package com.example.foodinfo.ui.decorator

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView


class ListVerticalItemDecoration(
    private val space: Int = 0, private val margin: Int = 0
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0) {
                top = margin
                bottom = space
            } else if (parent.getChildAdapterPosition(view) == state.itemCount - 1) {
                top = space
                bottom = margin
            } else {
                top = space
                bottom = space
            }
        }
    }
}