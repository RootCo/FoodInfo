package com.example.foodinfo.utils

import com.example.foodinfo.model.local.SearchFilter
import com.example.foodinfo.model.local.dao.filter_field.CategoryField
import com.example.foodinfo.model.local.dao.filter_field.NutrientField
import com.example.foodinfo.model.local.dao.filter_field.RangeField


fun queryExample() {
    val filter = SearchFilter()
    filter.categoryFields.add(
        CategoryField(CategoryField.fromLabel("diet"), listOf("low-fat", "low-carb"))
    )
    filter.categoryFields.add(
        CategoryField(CategoryField.fromLabel("cuisine"), listOf("Japanese", "Chinese"))
    )

    filter.rangeFields.add(
        RangeField(RangeField.fromLabel("calories"), 200, 600)
    )
    filter.rangeFields.add(
        RangeField(RangeField.Fields.TOTAL_TIME, maxValue = 30)
    )
    filter.rangeFields.add(
        RangeField(RangeField.Fields.TOTAL_INGREDIENTS, minValue = 5)
    )

    filter.nutrientFields.add(
        NutrientField("Protein", minValue = 3f)
    )
    filter.nutrientFields.add(
        NutrientField("Carbs", maxValue = 2f)
    )
    filter.nutrientFields.add(
        NutrientField("Fat", 1f, 2f)
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