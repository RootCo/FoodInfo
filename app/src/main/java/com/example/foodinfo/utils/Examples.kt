package com.example.foodinfo.utils

import com.example.foodinfo.model.local.SearchFilter
import com.example.foodinfo.model.local.dao.filter_field.CategoryField
import com.example.foodinfo.model.local.dao.filter_field.NutrientField
import com.example.foodinfo.model.local.dao.filter_field.RangeField


fun queryExample() {
    val filter = SearchFilter()
    filter.setSelector(SearchFilter.RECIPE_SELECTOR_EXTENDED)
    filter.categoryFields.add(
        CategoryField("cuisine", listOf("Japanese"))
    )
    filter.categoryFields.add(
        CategoryField("dish", listOf("omelet", "egg"))
    )
    filter.categoryFields.add(
        CategoryField("meal", listOf("breakfast", "dinner"))
    )

    filter.rangeFields.add(
        RangeField(RangeField.fromLabel("calories"), 1343, 1345)
    )
    filter.rangeFields.add(
        RangeField(RangeField.fromLabel("time"), minValue = 30)
    )
    filter.rangeFields.add(
        RangeField(RangeField.fromLabel("servings"), maxValue = 4)
    )

    filter.nutrientFields.add(
        NutrientField("Protein", minValue = 18f)
    )
    filter.nutrientFields.add(
        NutrientField("Carbs", maxValue = 18f)
    )
    filter.nutrientFields.add(
        NutrientField("Fat", 16f, 16.5f)
    )
    filter.buildQuery()

    /*
    >>>filter.query
        SELECT * FROM recipe WHERE
            total_time >= 40
            AND servings <= 4
            AND calories BETWEEN 1343 AND 1345
            AND id IN (SELECT nutrient_recipe_id FROM recipe_nutrients WHERE CASE
                    WHEN label = 'Fat' THEN total_value BETWEEN 16.0 AND 16.5
                    WHEN label = 'Carbs' THEN total_value <= 18.0
                    WHEN label = 'Protein' THEN total_value >= 20.0
                    ELSE NULL END
                GROUP BY nutrient_recipe_id
                HAVING  count(nutrient_recipe_id) = 3)
            AND id IN (SELECT label_recipe_id FROM recipe_labels WHERE CASE
                    WHEN category = 'cuisine' THEN label IN ('Japanese')
                    WHEN category = 'dish' THEN label IN ('omelet', 'egg')
                    WHEN category = 'meal' THEN label IN ('breakfast', 'dinner')
                    ELSE NULL END
                GROUP BY label_recipe_id
                HAVING  count(label_recipe_id) = 5)

    // will return recipe with ID: 1MMVGIN7W58TZAXUI8C8
     */
}