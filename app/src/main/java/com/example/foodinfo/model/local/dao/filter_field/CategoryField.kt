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
     * @param displayName database table column name that will be presented to user
     * @param tableName database table name for specific category
     * @param childKey foreign key that references to recipe this category belongs
     * @param column database table column name that will be used in query
     * @param icons list of all icon names from drawable resources
     * @param labels list of all available labels that can be used in query
     */
    enum class Fields(
        val displayName: String,
        val tableName: String,
        val childKey: String,
        val column: String,
        val icons: ArrayList<String>,
        val labels: ArrayList<String>
    ) {
        MEAL_TYPE(
            "meal",
            MealType.TABLE_NAME,
            MealType.Columns.RECIPE_ID,
            MealType.Columns.LABEL,
            MealType.ICONS,
            MealType.LABELS
        ),
        DISH_TYPE(
            "dish",
            DishType.TABLE_NAME,
            DishType.Columns.RECIPE_ID,
            DishType.Columns.LABEL,
            DishType.ICONS,
            DishType.LABELS
        ),
        DIET_TYPE(
            "diet",
            DietType.TABLE_NAME,
            DietType.Columns.RECIPE_ID,
            DietType.Columns.LABEL,
            DietType.ICONS,
            DietType.LABELS
        ),
        HEALTH_TYPE(
            "health",
            HealthType.TABLE_NAME,
            HealthType.Columns.RECIPE_ID,
            HealthType.Columns.LABEL,
            HealthType.ICONS,
            HealthType.LABELS
        ),
        CUISINE_TYPE(
            "cuisine",
            CuisineType.TABLE_NAME,
            CuisineType.Columns.RECIPE_ID,
            CuisineType.Columns.LABEL,
            CuisineType.ICONS,
            CuisineType.LABELS
        )
    }

    companion object {
        fun fromLabel(label: String) =
            Fields.values().first { it.displayName == label }
    }
}

class CategoryFields(_data: MutableSet<CategoryField> = mutableSetOf()) :
    BaseFieldSet<CategoryField>(_data)