package com.example.foodinfo.local.dto


open class LabelRecipeAttrDB(
    open val ID: Int,
    open val categoryID: Int,
    open val tag: String,
    open val name: String,
    open val description: String,
    open val previewURL: String
) {

    object Columns {
        const val ID = "id"
        const val CATEGORY_ID = "category_id"
        const val TAG = "tag"
        const val NAME = "name"
        const val DESCRIPTION = "description"
        const val PREVIEW_URL = "preview_url"
    }

    companion object {
        const val TABLE_NAME = "label_recipe_attr"
    }
}