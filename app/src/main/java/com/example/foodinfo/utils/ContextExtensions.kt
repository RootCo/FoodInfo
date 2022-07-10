package com.example.foodinfo.utils

import android.content.Context
import com.example.foodinfo.di.BaseApplication
import com.example.foodinfo.di.BaseApplicationComponent

val Context.applicationComponent: BaseApplicationComponent
    get() = when (this) {
        is BaseApplication -> applicationComponent
        else               -> this.applicationContext.applicationComponent
    }
