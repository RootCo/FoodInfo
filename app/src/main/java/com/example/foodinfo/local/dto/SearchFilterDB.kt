package com.example.foodinfo.local.dto


open class SearchFilterDB(
    open val name: String = DEFAULT_NAME
) {
    companion object {
        const val DEFAULT_NAME = "default_name"
    }
}