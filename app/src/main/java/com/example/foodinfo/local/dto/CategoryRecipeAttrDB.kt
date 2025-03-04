package com.example.foodinfo.local.dto


open class CategoryRecipeAttrDB(
    open val ID: Int,
    open val tag: String,
    open val name: String,
    open val previewURL: String
) {

    object Columns {
        const val ID = "id"
        const val TAG = "tag"
        const val NAME = "name"
        const val PREVIEW_URL = "preview_url"
    }

    companion object {
        const val TABLE_NAME = "category_recipe_attr"
    }
}