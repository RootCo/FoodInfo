package com.example.foodinfo.ui.custom_view

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView


class NonScrollListView(context: Context, attrs: AttributeSet?) :
    RecyclerView(context, attrs) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val expandSpec = MeasureSpec.makeMeasureSpec(
            Int.MAX_VALUE shr 2,
            MeasureSpec.AT_MOST
        )
        super.onMeasure(widthMeasureSpec, expandSpec)
    }
}
