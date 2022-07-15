package com.example.foodinfo.model.local.dao.filter_field

import com.example.foodinfo.model.local.entities.recipe_field.*


data class CategoryField(
    val value: Fields,
    val labels: List<String>
) {
    enum class Fields(
        val tableName: String,
        val childKey: String,
        val column: String,
        val label: String,
        val validLabels: List<String>
    ) {
        MEAL_TYPE(
            MealType.TABLE_NAME,
            MealType.Columns.RECIPE_ID,
            MealType.Columns.LABEL,
            "meal",
            MealType.validLabels
        ),
        DISH_TYPE(
            DishType.TABLE_NAME,
            DishType.Columns.RECIPE_ID,
            DishType.Columns.LABEL,
            "dish",
            DishType.validLabels
        ),
        DIET_TYPE(
            DietType.TABLE_NAME,
            DietType.Columns.RECIPE_ID,
            DietType.Columns.LABEL,
            "diet",
            DietType.validLabels
        ),
        HEALTH_TYPE(
            HealthType.TABLE_NAME,
            HealthType.Columns.RECIPE_ID,
            HealthType.Columns.LABEL,
            "health",
            HealthType.validLabels
        ),
        CUISINE_TYPE(
            CuisineType.TABLE_NAME,
            CuisineType.Columns.RECIPE_ID,
            CuisineType.Columns.LABEL,
            "cuisine",
            CuisineType.validLabels
        )
    }

    companion object {
        fun fromLabel(label: String) =
            Fields.values().first { it.label == label }
    }
}

class CategoryFields(private val _data: MutableSet<CategoryField> = mutableSetOf()) {
    val data: MutableSet<CategoryField>
        get() = _data

    fun add(field: CategoryField) {
        _data.add(field)
    }
}