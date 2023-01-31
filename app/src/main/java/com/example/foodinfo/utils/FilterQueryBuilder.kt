package com.example.foodinfo.utils

import com.example.foodinfo.local.room.entity.*
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
                field.infoID,
                field.minValue,
                field.maxValue
            )
        }
        var query = "${RecipeEntity.Columns.ID} IN "
        query += "(SELECT ${NutrientOfRecipeEntity.Columns.RECIPE_ID} "
        query += "FROM ${NutrientOfRecipeEntity.TABLE_NAME} WHERE CASE "
        query += nutrientQueryList.joinToString(" ")
        query += " ELSE NULL END "
        query += "GROUP BY ${NutrientOfRecipeEntity.Columns.RECIPE_ID} "
        query += "HAVING  count(${NutrientOfRecipeEntity.Columns.RECIPE_ID}) = ${nutrientQueryList.size})"
        return query
    }

    private fun nutrientFieldToQuery(
        infoID: Int, minValue: Float?, maxValue: Float?
    ): String {
        var query = ""
        query += "WHEN ${NutrientOfRecipeEntity.Columns.INFO_ID} = '$infoID' THEN "
        query += rangeFieldToQuery(
            NutrientOfRecipeEntity.Columns.TOTAL_VALUE,
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
        val labels = categoryFilterFields.flatMap { it.labelInfoIDs }
        if (labels.isEmpty()) return ""

        var query = "${RecipeEntity.Columns.ID} IN "
        query += "(SELECT ${LabelOfRecipeEntity.Columns.RECIPE_ID} FROM "
        query += "(SELECT ${LabelOfRecipeEntity.Columns.RECIPE_ID}, ${LabelRecipeAttrEntity.Columns.CATEGORY_ID} "
        query += "FROM ${LabelOfRecipeEntity.TABLE_NAME} INNER JOIN ${LabelRecipeAttrEntity.TABLE_NAME} "
        query += "ON ${LabelOfRecipeEntity.Columns.INFO_ID} = "
        query += "${LabelRecipeAttrEntity.TABLE_NAME}.${LabelRecipeAttrEntity.Columns.ID} "
        query += "WHERE ${LabelOfRecipeEntity.Columns.INFO_ID} IN (${labels.joinToString(", ")}) "
        query += "GROUP BY ${LabelOfRecipeEntity.Columns.RECIPE_ID}, ${LabelRecipeAttrEntity.Columns.CATEGORY_ID}) "
        query += "GROUP BY ${LabelOfRecipeEntity.Columns.RECIPE_ID} "
        query += "HAVING count(${LabelOfRecipeEntity.Columns.RECIPE_ID}) = ${categoryFilterFields.size})"
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
                field.columnName,
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