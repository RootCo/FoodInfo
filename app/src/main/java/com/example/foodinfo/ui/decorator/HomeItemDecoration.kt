package com.example.foodinfo.ui.decorator

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class HomeItemDecoration(private val spaceSize: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {/*
        делаем отступ между элементами в HoleRecyclerView 18dp
        для краевых элементов делаем отступ с соответствующего края 72dp
        это нужно чтобы краевой элемент оказался ровно в центре экрана
        для доп информации см getHomeRecipeWidth() в Utils
         */
        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0) {
                left = spaceSize * 4
                right = spaceSize
            } else if (parent.getChildAdapterPosition(view) == state.itemCount - 1) {
                left = spaceSize
                right = spaceSize * 4
            } else {
                left = spaceSize
                right = spaceSize
            }
        }
    }
}