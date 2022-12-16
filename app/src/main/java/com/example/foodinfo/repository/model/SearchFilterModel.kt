package com.example.foodinfo.repository.model

import com.example.foodinfo.local.entity.LabelRecipeEntity
import com.example.foodinfo.local.entity.NutrientRecipeEntity
import com.example.foodinfo.local.entity.RecipeEntity
import com.example.foodinfo.repository.model.filter_field.BaseField
import com.example.foodinfo.repository.model.filter_field.CategoryField
import com.example.foodinfo.repository.model.filter_field.NutrientField
import com.example.foodinfo.utils.queryExample


/**
 * Class that generate query for [RecipeEntity]
 *
 * Fields order:
 * * Range fields - fastest one, search goes through the fields of [RecipeEntity] itself
 * * Nutrient fields - slowly than Range fields but only one query to another table
 * for multiple nutrients
 * * Category fields - the slowest one, each category requires
 * subquery to another tables
 *
 * Because of AND separator between subqueries if any of subquery don't
 * match the condition, row will be denied, so it makes sense to check the fastest
 * conditions first
 *
 * @sample queryExample
 */
data class SearchFilterModel(
    val id: Long = 0,
    val name: String = "target",
    val baseFields: List<BaseField> = listOf(),
    val categoryFields: List<CategoryField> = listOf(),
    val nutrientFields: List<NutrientField> = listOf(),
    var inputText: String = "",
    private val separator: String = " AND ",
    private var _query: String = "",
) {
    val query: String
        get() = _query

    private fun nutrientFieldsToQuery(): String {
        if (nutrientFields.isEmpty()) return ""
        val nutrientQueryList = nutrientFields.map { field ->
            nutrientFieldToQuery(
                field.value,
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
        name: String, minValue: Double?, maxValue: Double?
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
        if (minValue == null && maxValue == null) return ""
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
        if (categoryFields.isEmpty()) return ""
        var query = "${RecipeEntity.Columns.ID} IN "
        val categoryQueryList = categoryFields.map { field ->
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
        query += "${categoryFields.sumOf { it.labels.size }}"
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

    fun buildQuery() {
        _query = ""
        val subQueryList = arrayListOf<String>()
        subQueryList.addAll(baseFields.map { field ->
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
        _query += "SELECT * FROM ${RecipeEntity.TABLE_NAME}"
        if (subQueryList.size > 0) {
            _query += " WHERE " + subQueryList.joinToString(separator)
        }
    }
}