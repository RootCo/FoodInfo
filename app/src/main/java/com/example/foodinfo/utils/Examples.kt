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
    SELECT recipe_id, category FROM recipe_labels WHERE CASE
        WHEN category = 'diet' THEN name IN ('high-fiber', 'high-protein')
        WHEN category = 'meal' THEN name IN ('lunch', 'dinner', 'snack')
        WHEN category = 'cuisine' THEN name IN ('Asian')
        ELSE NULL END
    GROUP BY recipe_id, category)
GROUP BY recipe_id
HAVING  count(recipe_id) = 3)



SELECT name FROM recipe WHERE id IN (SELECT recipe_id FROM (
    SELECT recipe_id, category_UID
    FROM recipe_labels INNER JOIN info_labels ON recipe_labels.info_UID = info_labels.UID
    WHERE info_UID IN ('high-fiber', 'high-protein', 'lunch', 'dinner', 'snack', 'Asian')
    GROUP BY recipe_id, category_UID)
GROUP BY recipe_id
HAVING  count(recipe_id) = 3)


data
    model // Package that contains all necessary data models for app (e.g. PersonModel)
    local
        dto // Package that contains all necessary data models for repo (e.g. PersonDB)
        dao // Interface that declares all necessary methods for repo. Return type: dto (e.g. PersonDB)
        room
            dao // Inherited from local.dao. Input: PersonEntity, Output: PersonSource
            entity (e.g. PersonEntity)
            source (inherited from local.dto) (e.g. PersonSource)
    remote
        dto (e.g. PersonAPI)
        ...
repository
    uses only dto objects (PersonDB and PersonAPI)
    return only model objects (PersonModel)

extensions
    mappers (remote_dto to local_dto, local_dto to model, model to local_dto)

 */
