package com.example.foodinfo.utils


fun queryExample() {
    /*
        SELECT * FROM recipe WHERE
            servings <= 10
            AND calories BETWEEN 200 AND 250
            AND id IN (SELECT recipe_id FROM nutrients WHERE CASE
                    WHEN label = 'Fat' THEN total_value BETWEEN 18.0 AND 20.5
                    WHEN label = 'Carbs' THEN total_value <= 6.0
                    WHEN label = 'Protein' THEN total_value >= 2.0
                    ELSE NULL END
                GROUP BY recipe_id
                HAVING  count(recipe_id) = 3)
            AND id IN (SELECT recipe_id FROM (
                SELECT recipe_id, category FROM recipe_labels WHERE CASE
                    WHEN category = 'diet' THEN name IN ('high-fiber', 'high-protein')
                    WHEN category = 'meal' THEN name IN ('lunch', 'dinner', 'snack')
                    WHEN category = 'cuisine' THEN name IN ('Asian')
                    ELSE NULL END
                GROUP BY recipe_id, category)
            GROUP BY recipe_id
            HAVING  count(recipe_id) = 3)
     */
}

/*
SELECT name FROM recipe WHERE id IN (SELECT recipe_id FROM (
    SELECT recipe_id, category, name FROM recipe_labels WHERE CASE
        WHEN category = 'diet' THEN name IN ('high-fiber', 'high-protein')
        WHEN category = 'meal' THEN name IN ('lunch', 'dinner', 'snack')
        WHEN category = 'cuisine' THEN name IN ('Asian')
        ELSE NULL END
    GROUP BY recipe_id, category)
GROUP BY recipe_id
HAVING  count(recipe_id) = 3)

 */