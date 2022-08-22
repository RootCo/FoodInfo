package com.example.foodinfo.utils

import android.content.Context
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import com.example.foodinfo.di.BaseApplication
import com.example.foodinfo.di.BaseApplicationComponent


// подсмотрел этот код у Android Broadcast
val Context.appComponent: BaseApplicationComponent
    get() = when (this) {
        is BaseApplication -> applicationComponent
        else               -> this.applicationContext.appComponent
    }

@ColorInt
fun Context.getAttrColor(
    @AttrRes
    attrColor: Int,
    typedValue: TypedValue = TypedValue(),
    resolveRefs: Boolean = true
): Int {
    theme.resolveAttribute(attrColor, typedValue, resolveRefs)
    return typedValue.data
}