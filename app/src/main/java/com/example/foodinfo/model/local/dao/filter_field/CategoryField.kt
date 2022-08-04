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
) : BaseField {
    /**
     * @param tableName database table name for specific category
     * @param childKey foreign key that references to recipe this category belongs
     * @param column database table column name that will be used in query
     * @param icons list of all icon names from drawable resources
     * @param label database table column name that will be presented to user
     * @param labels list of all available labels that can be used in query
     */
    enum class Fields(
        val tableName: String,
        val childKey: String,
        val column: String,
        val icons: ArrayList<String>,
        val label: String,
        val labels: ArrayList<String>
    ) {
        MEAL_TYPE(
            MealType.TABLE_NAME,
            MealType.Columns.RECIPE_ID,
            MealType.Columns.LABEL,
            MealType.ICONS,
            "meal",
            MealType.LABELS
        ),
        DISH_TYPE(
            DishType.TABLE_NAME,
            DishType.Columns.RECIPE_ID,
            DishType.Columns.LABEL,
            DishType.ICONS,
            "dish",
            DishType.LABELS
        ),
        DIET_TYPE(
            DietType.TABLE_NAME,
            DietType.Columns.RECIPE_ID,
            DietType.Columns.LABEL,
            DietType.ICONS,
            "diet",
            DietType.LABELS
        ),
        HEALTH_TYPE(
            HealthType.TABLE_NAME,
            HealthType.Columns.RECIPE_ID,
            HealthType.Columns.LABEL,
            HealthType.ICONS,
            "health",
            HealthType.LABELS
        ),
        CUISINE_TYPE(
            CuisineType.TABLE_NAME,
            CuisineType.Columns.RECIPE_ID,
            CuisineType.Columns.LABEL,
            CuisineType.ICONS,
            "cuisine",
            CuisineType.LABELS
        )
    }

    companion object {
        fun fromLabel(label: String) =
            Fields.values().first { it.label == label }
    }
}

class CategoryFields(_data: MutableSet<CategoryField> = mutableSetOf()) :
    BaseFieldSet<CategoryField>(_data)