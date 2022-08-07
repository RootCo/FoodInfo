package com.example.foodinfo.model.local.entities

import androidx.room.*
import com.example.foodinfo.model.local.RecipeExtendedDTO
import com.example.foodinfo.model.local.RecipeShortDTO
import com.example.foodinfo.model.local.dao.filter_field.*
import com.example.foodinfo.model.local.dao.type_converter.CategoryFieldTypeConverter
import com.example.foodinfo.model.local.dao.type_converter.NutrientFieldTypeConverter
import com.example.foodinfo.model.local.dao.type_converter.RangeFieldTypeConverter
import com.example.foodinfo.model.local.entities.recipe_field.Nutrient


/**
 * Class that generate query for [Recipe]
 *
 * Fields order:
 * * Range fields - fastest one, search goes through the fields of [Recipe] itself
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
@Entity(
    tableName = SearchFilter.TABLE_NAME,
    indices = [Index(value = arrayOf(SearchFilter.Columns.NAME), unique = true)]
)
data class SearchFilter(
    @ColumnInfo(name = Columns.ID)
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = Columns.NAME)
    private var _name: String = "target",

    @ColumnInfo(name = Columns.INPUT_TEXT)
    var inputText: String = "",

    @ColumnInfo(name = Columns.RANGE_FIELDS)
    @TypeConverters(RangeFieldTypeConverter::class)
    var rangeFields: RangeFields = RangeFields(),

    @ColumnInfo(name = Columns.CATEGORY_FIELDS)
    @TypeConverters(CategoryFieldTypeConverter::class)
    var categoryFields: CategoryFields = CategoryFields(),

    @ColumnInfo(name = Columns.NUTRIENT_FIELDS)
    @TypeConverters(NutrientFieldTypeConverter::class)
    var nutrientFields: NutrientFields = NutrientFields(),
) {
    @Ignore
    private var _query: String = ""

    @Ignore
    private var selector: String = RecipeShortDTO.SELECTOR

    @Ignore
    private val separator: String = " AND "


    val query: String
        get() = _query

    var name: String
        get() = _name
        set(value) {
            if (value != "target")
                _name = value
        }

    private fun nutrientFieldsToQuery(nutrientQueryList: List<String>): String {
        if (nutrientQueryList.isEmpty()) return ""
        var query = "${Recipe.Columns.ID} IN "
        query += "("
        query += "SELECT ${Nutrient.Columns.RECIPE_ID} FROM ${Nutrient.TABLE_NAME} WHERE CASE "
        query += nutrientQueryList.joinToString(" ")
        query += " ELSE NULL END "
        query += "GROUP BY ${Nutrient.Columns.RECIPE_ID} "
        query += "HAVING  count(${Nutrient.Columns.RECIPE_ID}) = ${nutrientQueryList.size})"
        return query
    }

    private fun nutrientFieldToQuery(
        name: String, minValue: Float?, maxValue: Float?
    ): String {
        var query = ""
        query += "WHEN ${Nutrient.Columns.LABEL} = '$name' THEN "
        query += rangeFieldToQuery(Nutrient.Columns.QUANTITY, minValue, maxValue)
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

    private fun categoryFieldToQuery(
        tableName: String, childKey: String, column: String, labels: List<String>
    ): String {
        var query = "${Recipe.Columns.ID} IN "
        query += "("
        query += "SELECT $childKey FROM $tableName "
        query += "WHERE $column IN ('${labels.joinToString("', '")}')"
        query += ")"
        return query
    }

    private fun inputTextToQuery(): String {
        if (inputText == "") return ""
        return "${Recipe.Columns.NAME} LIKE '$inputText'"
    }


    fun setSelector(id: Int) {
        when (id) {
            RECIPE_SELECTOR_SHORT    -> selector = RecipeShortDTO.SELECTOR
            RECIPE_SELECTOR_EXTENDED -> selector = RecipeExtendedDTO.SELECTOR
        }
    }

    fun buildQuery() {
        _query = ""
        val subQueryList = arrayListOf<String>()
        subQueryList.addAll(rangeFields.data.map { field ->
            rangeFieldToQuery(
                field.value.column,
                field.minValue,
                field.maxValue
            )
        })
        val nutrientQueryList = nutrientFields.data.map { field ->
            nutrientFieldToQuery(
                field.value.label,
                field.minValue,
                field.maxValue
            )
        }
        subQueryList.addAll(categoryFields.data.map { field ->
            categoryFieldToQuery(
                field.value.tableName,
                field.value.childKey,
                field.value.column,
                field.labels
            )
        })
        subQueryList.add(nutrientFieldsToQuery(nutrientQueryList))
        subQueryList.add(inputTextToQuery())
        subQueryList.removeAll(setOf(""))
        _query += selector
        if (subQueryList.size > 0) {
            _query += " WHERE " + subQueryList.joinToString(separator)
        }
    }


    object Columns {
        const val ID = "id"
        const val NAME = "name"
        const val INPUT_TEXT = "input_text"
        const val RANGE_FIELDS = "range_fields"
        const val CATEGORY_FIELDS = "category_fields"
        const val NUTRIENT_FIELDS = "nutrient_fields"
    }

    companion object {
        const val TABLE_NAME = "filter_search"
        const val RECIPE_SELECTOR_SHORT = 0
        const val RECIPE_SELECTOR_EXTENDED = 1
        const val SELECTOR = "SELECT * FROM $TABLE_NAME"
    }
}

fun queryExample() {
    val filter = SearchFilter()
    filter.categoryFields.data.add(
        CategoryField(CategoryField.Fields.DIET_TYPE, listOf("low-fat", "low-carb"))
    )
    filter.categoryFields.data.add(
        CategoryField(CategoryField.Fields.CUISINE_TYPE, listOf("Japanese", "Chinese"))
    )

    filter.rangeFields.data.add(
        RangeField(RangeField.Fields.CALORIES, 200, 600)
    )
    filter.rangeFields.data.add(
        RangeField(RangeField.Fields.TOTAL_TIME, maxValue = 30)
    )
    filter.rangeFields.data.add(
        RangeField(RangeField.Fields.TOTAL_INGREDIENTS, minValue = 5)
    )

    filter.nutrientFields.data.add(
        NutrientField(NutrientField.fromLabel("Protein"), minValue = 3f)
    )
    filter.nutrientFields.data.add(
        NutrientField(NutrientField.fromLabel("Carbs"), maxValue = 2f)
    )
    filter.nutrientFields.data.add(
        NutrientField(NutrientField.fromLabel("Fat"), 1f, 2f)
    )
    filter.buildQuery()


    /*
    >>>filter.query
        SELECT * FROM recipe WHERE
        calories BETWEEN 200 AND 600
        AND total_time <= 30
        AND ingredients >= 5
        AND id IN (SELECT  nutrient_recipe_id FROM nutrient WHERE CASE
                WHEN label = 'Protein' THEN quantity >= 3
                WHEN label = 'Carbs' THEN quantity <= 2
                WHEN label = 'Fat' THEN quantity BETWEEN 1 AND
                ELSE NULL END
            GROUP BY nutrient_recipe_id
            HAVING count(nutrient_recipe_id) = 3)
        AND id IN (SELECT diet_recipe_id FROM diet_type WHERE label IN
            ('low-fat', 'low-carb'))
        AND id IN (SELECT cuisine_recipe_id FROM cuisine_type WHERE label IN
            ('Japanese', 'Chinese'))
     */
}