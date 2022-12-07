package com.example.foodinfo.ui.decorator

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView


class ListHorizontalItemDecoration(
    private val space: Int, private val margin: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0) {
                right = space
                left = margin
            } else if (parent.getChildAdapterPosition(view) == state.itemCount - 1) {
                left = space
                right = margin
            } else {
                left = space
                right = space
            }
        }
    }
}