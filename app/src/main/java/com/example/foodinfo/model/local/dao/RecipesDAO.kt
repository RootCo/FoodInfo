package com.example.foodinfo.model.local.dao

import androidx.paging.PagingSource
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.foodinfo.model.local.entities.RecipeExtendedEntity
import com.example.foodinfo.model.local.entities.RecipeShortEntity
import com.example.foodinfo.model.local.entities.RecipeEntity
import com.example.foodinfo.model.local.entities.recipe_field.*
import kotlinx.coroutines.flow.Flow


@Dao
interface RecipesDAO {
    @Query(
        "SELECT * FROM ${RecipeEntity.TABLE_NAME} " +
                "WHERE ${RecipeEntity.Columns.ID} " +
                "IN (SELECT ${RecipeEntity.Columns.ID} " +
                "FROM ${RecipeEntity.TABLE_NAME} ORDER BY RANDOM())"
    )
    fun getPopular(): PagingSource<Int, RecipeShortEntity>

    @RawQuery(
        observedEntities = [
            RecipeEntity::class,
            RecipeNutrientEntity::class,
            RecipeIngredientEntity::class,
            RecipeDietLabelEntity::class,
            RecipeDishLabelEntity::class,
            RecipeMealLabelEntity::class,
            RecipeHealthLabelEntity::class,
            RecipeCuisineLabelEntity::class,
            RecipeCuisineLabelEntity::class,
        ]
    )
    fun getByFilterShort(query: SupportSQLiteQuery): PagingSource<Int, RecipeShortEntity>

    @Query(
        "${RecipeExtendedEntity.SELECTOR} " +
                "WHERE ${RecipeEntity.Columns.ID} " +
                "LIKE '%' || :id || '%'"
    )
    fun getByIdExtended(id: String): Flow<RecipeExtendedEntity>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addRecipe(recipe: RecipeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addRecipes(recipes: List<RecipeEntity>)


    @Insert
    fun addNutrients(nutrients: List<RecipeNutrientEntity>)

    @Insert
    fun addIngredients(nutrients: List<RecipeIngredientEntity>)

    @Insert
    fun addDietTypes(dietLabels: List<RecipeDietLabelEntity>)

    @Insert
    fun addDishTypes(dishLabels: List<RecipeDishLabelEntity>)

    @Insert
    fun addMealTypes(mealLabels: List<RecipeMealLabelEntity>)

    @Insert
    fun addHealthTypes(healthLabels: List<RecipeHealthLabelEntity>)

    @Insert
    fun addCuisineTypes(cuisineTypes: List<RecipeCuisineLabelEntity>)

    @Transaction
    fun addAll(
        recipes: List<RecipeEntity>,
        nutrients: List<RecipeNutrientEntity>,
        ingredients: List<RecipeIngredientEntity>,
        dietLabels: List<RecipeDietLabelEntity>,
        dishLabels: List<RecipeDishLabelEntity>,
        mealLabels: List<RecipeMealLabelEntity>,
        healthLabels: List<RecipeHealthLabelEntity>,
        cuisineTypes: List<RecipeCuisineLabelEntity>,
    ) {
        addRecipes(recipes)
        addNutrients(nutrients)
        addIngredients(ingredients)
        addDietTypes(dietLabels)
        addDishTypes(dishLabels)
        addMealTypes(mealLabels)
        addHealthTypes(healthLabels)
        addCuisineTypes(cuisineTypes)
    }
}
