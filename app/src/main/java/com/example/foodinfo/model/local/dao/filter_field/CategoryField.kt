package com.example.foodinfo.model.local.dao.filter_field

import com.example.foodinfo.model.local.dao.filter_field.CategoryField.Fields
import com.example.foodinfo.model.local.entities.recipe_field.*


/**
 * @param value [Fields] object that contains all necessary information for
 * query generation
 * @param labels list of all labels that must be present in the recipe
 */
data class CategoryField(
    val value: Fields,
    val labels: List<String>
) {

    /**
     * @param label database table column name that will be presented to user
     * @param tableName database table name for specific category
     * @param childKey foreign key that references to recipe this category belongs
     * @param column database table column name that will be used in query
     */
    enum class Fields(
        val label: String,
        val tableName: String,
        val childKey: String,
        val column: String
    ) {
        MEAL_TYPE(
            RecipeMealLabelEntity.DISPLAY_NAME,
            RecipeMealLabelEntity.TABLE_NAME,
            RecipeMealLabelEntity.Columns.RECIPE_ID,
            RecipeMealLabelEntity.Columns.LABEL
        ),
        DISH_TYPE(
            RecipeDishLabelEntity.DISPLAY_NAME,
            RecipeDishLabelEntity.TABLE_NAME,
            RecipeDishLabelEntity.Columns.RECIPE_ID,
            RecipeDishLabelEntity.Columns.LABEL
        ),
        DIET_TYPE(
            RecipeDietLabelEntity.DISPLAY_NAME,
            RecipeDietLabelEntity.TABLE_NAME,
            RecipeDietLabelEntity.Columns.RECIPE_ID,
            RecipeDietLabelEntity.Columns.LABEL
        ),
        HEALTH_TYPE(
            RecipeHealthLabelEntity.DISPLAY_NAME,
            RecipeHealthLabelEntity.TABLE_NAME,
            RecipeHealthLabelEntity.Columns.RECIPE_ID,
            RecipeHealthLabelEntity.Columns.LABEL
        ),
        CUISINE_TYPE(
            RecipeCuisineLabelEntity.DISPLAY_NAME,
            RecipeCuisineLabelEntity.TABLE_NAME,
            RecipeCuisineLabelEntity.Columns.RECIPE_ID,
            RecipeCuisineLabelEntity.Columns.LABEL
        )
    }

    companion object {
        fun fromLabel(label: String) = Fields.values().first { it.label == label }
    }
}