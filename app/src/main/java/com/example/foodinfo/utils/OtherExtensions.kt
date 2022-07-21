package com.example.foodinfo.utils

import android.os.Parcelable
import androidx.recyclerview.widget.RecyclerView


fun RecyclerView.restoreState(parcelable: Parcelable?) {
    parcelable ?: return
    layoutManager?.onRestoreInstanceState(parcelable)
}
