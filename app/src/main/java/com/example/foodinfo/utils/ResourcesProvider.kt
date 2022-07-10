package com.example.foodinfo.utils

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources
import javax.inject.Inject

class ResourcesProvider @Inject constructor(private val context: Context) {

    fun getDrawableById(id: Int): Drawable? {
        return context.getDrawable(id)
    }

    fun getDrawableByName(name: String): Drawable? {
        val resourceId = context.resources.getIdentifier(
            name, "drawable", context.packageName
        )
        if (resourceId != 0) {
            return AppCompatResources.getDrawable(context, resourceId)
        }
        return null
    }
}