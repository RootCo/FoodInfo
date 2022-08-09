package com.example.foodinfo.utils

import android.graphics.drawable.Drawable
import android.os.Parcelable
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView


fun RecyclerView.restoreState(parcelable: Parcelable?) {
    parcelable ?: return
    layoutManager?.onRestoreInstanceState(parcelable)
}

fun View.getDrawableByName(name: String): Drawable? {
    val resourceId = context.resources.getIdentifier(
        name, "drawable", context.packageName
    )
    if (resourceId != 0) {
        return AppCompatResources.getDrawable(context, resourceId)
    }
    return null
}