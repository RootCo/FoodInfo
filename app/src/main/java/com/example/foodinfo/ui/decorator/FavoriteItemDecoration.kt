package com.example.foodinfo.ui.decorator

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView


class FavoriteItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0) {
                bottom = space
            } else if (parent.getChildAdapterPosition(view) == state.itemCount - 1) {
                top = space
            } else {
                top = space
                bottom = space
            }
        }
    }
}