package com.example.foodinfo.ui.decorator

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ExploreOuterItemDecoration(
    private val space: Int, private val margin: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0) {
                bottom = space
                top = margin
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