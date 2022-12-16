package com.example.foodinfo.utils

import com.example.foodinfo.local.entity.LabelRecipeEntity
import com.example.foodinfo.local.entity.NutrientRecipeEntity
import com.example.foodinfo.local.entity.RecipeEntity
import com.example.foodinfo.local.entity.SearchFilterEntity
import com.example.foodinfo.repository.model.filter_field.BaseFilterField
import com.example.foodinfo.repository.model.filter_field.CategoryFilterField
import com.example.foodinfo.repository.model.filter_field.NutrientFilterField


/**
 * Class that generate query by [SearchFilterEntity]
 *
 * Fields order:
 * * Base fields - fastest one, search goes through the fields of [RecipeEntity] itself
 * * Nutrient fields - slowly than Range fields but only one query to another table
 * for multiple nutrients
 * * Category fields - the slowest one, also only one query to another table
 * for multiple categories but each value needs to be compared against every element in list
 *
 * Because of AND separator between subqueries if any of subquery don't
 * match the condition, row will be denied, so it makes sense to check the fastest
 * conditions first
 *
 * @sample queryExample
 */
data class FilterQueryBuilder(
    val baseFilterFields: List<BaseFilterField> = listOf(),
    val categoryFilterFields: List<CategoryFilterField> = listOf(),
    val nutrientFilterFields: List<NutrientFilterField> = listOf()
) {
    private var inputText: String = ""


    private fun nutrientFieldsToQuery(): String {
        if (nutrientFilterFields.isEmpty()) return ""
        val nutrientQueryList = nutrientFilterFields.map { field ->
            nutrientFieldToQuery(
                field.name,
                field.minValue,
                field.maxValue
            )
        }
        var query = "${RecipeEntity.Columns.ID} IN "
        query += "("
        query += "SELECT ${NutrientRecipeEntity.Columns.RECIPE_ID} "
        query += "FROM ${NutrientRecipeEntity.TABLE_NAME} WHERE CASE "
        query += nutrientQueryList.joinToString(" ")
        query += " ELSE NULL END "
        query += "GROUP BY ${NutrientRecipeEntity.Columns.RECIPE_ID} "
        query += "HAVING  count(${NutrientRecipeEntity.Columns.RECIPE_ID}) = ${nutrientQueryList.size}"
        query += ")"
        return query
    }

    private fun nutrientFieldToQuery(
        name: String, minValue: Float?, maxValue: Float?
    ): String {
        var query = ""
        query += "WHEN ${NutrientRecipeEntity.Columns.NAME} = '$name' THEN "
        query += rangeFieldToQuery(
            NutrientRecipeEntity.Columns.TOTAL_VALUE,
            minValue,
            maxValue
        )
        return query
    }

    private fun rangeFieldToQuery(
        column: String, minValue: Any?, maxValue: Any?
    ): String {
        return if (minValue == null) {
            "$column <= $maxValue"
        } else if (maxValue == null) {
            "$column >= $minValue"
        } else {
            when (minValue == maxValue) {
                true  -> "$column = $maxValue"
                false -> "$column BETWEEN $minValue AND $maxValue"
            }
        }
    }

    private fun categoryFieldsToQuery(): String {
        var query = "${RecipeEntity.Columns.ID} IN "
        val categoryQueryList = categoryFilterFields.map { field ->
            categoryFieldToQuery(
                field.name,
                field.labels
            )
        }
        query += "("
        query += "SELECT ${LabelRecipeEntity.Columns.RECIPE_ID} "
        query += "FROM ${LabelRecipeEntity.TABLE_NAME} WHERE CASE "
        query += categoryQueryList.joinToString(" ")
        query += " ELSE NULL END "
        query += "GROUP BY ${LabelRecipeEntity.Columns.RECIPE_ID} "
        query += "HAVING  count(${LabelRecipeEntity.Columns.RECIPE_ID}) = "
        query += "${categoryFilterFields.sumOf { it.labels.size }}"
        query += ")"
        return query
    }

    private fun categoryFieldToQuery(
        column: String, labels: List<String>
    ): String {
        var query = ""
        query += "WHEN ${LabelRecipeEntity.Columns.CATEGORY} = '$column' THEN "
        query += "${LabelRecipeEntity.Columns.NAME} IN ('${labels.joinToString("', '")}')"
        return query
    }

    private fun inputTextToQuery(): String {
        if (inputText == "") return ""
        return "${RecipeEntity.Columns.NAME} LIKE '%$inputText%'"
    }

    fun setInputText(text: String) {
        inputText = text
    }

    fun getQuery(): String {
        var query = ""
        val subQueryList = arrayListOf<String>()
        subQueryList.addAll(baseFilterFields.map { field ->
            rangeFieldToQuery(
                field.name,
                field.minValue,
                field.maxValue
            )
        })
        subQueryList.add(nutrientFieldsToQuery())
        subQueryList.add(categoryFieldsToQuery())
        subQueryList.add(inputTextToQuery())
        subQueryList.removeAll(setOf(""))
        query += "SELECT * FROM ${RecipeEntity.TABLE_NAME}"
        if (subQueryList.size > 0) {
            query += " WHERE " + subQueryList.joinToString(SEPARATOR)
        }
        return query
    }


    companion object {
        private const val SEPARATOR = " AND "
    }
}