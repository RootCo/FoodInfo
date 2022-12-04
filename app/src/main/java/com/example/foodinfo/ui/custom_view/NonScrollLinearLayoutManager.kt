package com.example.foodinfo.ui.custom_view

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager


class NonScrollLinearLayoutManager(context: Context?) :
    LinearLayoutManager(context) {
    var isScrollEnabled = true

    override fun canScrollHorizontally(): Boolean {
        return isScrollEnabled && super.canScrollHorizontally()
    }

    override fun canScrollVertically(): Boolean {
        return isScrollEnabled && super.canScrollVertically()
    }
}