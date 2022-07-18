package com.example.foodinfo.model.local.dao

import androidx.paging.PagingSource
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.foodinfo.model.local.RecipeExplore
import com.example.foodinfo.model.local.RecipeExtended
import com.example.foodinfo.model.local.RecipeResult
import com.example.foodinfo.model.local.entities.Recipe
import com.example.foodinfo.model.local.entities.recipe_field.*
import kotlinx.coroutines.flow.Flow


@Dao
interface RecipesDAO {
    @Query(
        "SELECT * FROM ${Recipe.TABLE_NAME} " +
                "WHERE ${Recipe.Columns.ID} " +
                "IN (SELECT ${Recipe.Columns.ID} " +
                "FROM ${Recipe.TABLE_NAME} ORDER BY RANDOM() LIMIT 1)"
    )
    fun getDaily(): Flow<RecipeExplore>

    @Query(
        "SELECT * FROM ${Recipe.TABLE_NAME} " +
                "WHERE ${Recipe.Columns.ID} " +
                "IN (SELECT ${Recipe.Columns.ID} " +
                "FROM ${Recipe.TABLE_NAME} ORDER BY RANDOM())"
    )
    fun getPopular(): PagingSource<Int, RecipeExplore>

    @Query("${RecipeExtended.SELECTOR} WHERE ${Recipe.Columns.ID} == :id")
    fun getById(id: String): RecipeExtended

    @RawQuery
    fun getByFilterResult(query: SupportSQLiteQuery): List<RecipeResult>

    @RawQuery
    fun getByFilterExplore(query: SupportSQLiteQuery): List<RecipeExplore>

    @Transaction
    @RawQuery
    fun getByFilterExtended(query: SupportSQLiteQuery): List<RecipeExtended>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addRecipe(recipe: Recipe)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addRecipes(recipes: List<Recipe>)

    /*
        эти методы не относятся к таблице рецептов и по хорошему должны быть
        в отдельных Dao, но это будет просто лишние 7 классов с 1 методом т.к.
        эти таблицы не являются самостоятельными и чтение/запись в них будет
        происходить ТОЛЬКО при чтении/записи рецепта
     */
    @Insert
    fun addNutrients(nutrients: List<Nutrient>)

    @Insert
    fun addIngredients(nutrients: List<Ingredient>)

    @Insert
    fun addDietTypes(dietTypes: List<DietType>)

    @Insert
    fun addDishTypes(dishTypes: List<DishType>)

    @Insert
    fun addMealTypes(mealTypes: List<MealType>)

    @Insert
    fun addHealthTypes(healthTypes: List<HealthType>)

    @Insert
    fun addCuisineTypes(cuisineTypes: List<CuisineType>)

    @Transaction
    fun addAll(
        recipes: List<Recipe>,
        nutrients: List<Nutrient>,
        ingredients: List<Ingredient>,
        dietTypes: List<DietType>,
        dishTypes: List<DishType>,
        mealTypes: List<MealType>,
        healthTypes: List<HealthType>,
        cuisineTypes: List<CuisineType>,
    ) {
        addRecipes(recipes)
        addNutrients(nutrients)
        addIngredients(ingredients)
        addDietTypes(dietTypes)
        addDishTypes(dishTypes)
        addMealTypes(mealTypes)
        addHealthTypes(healthTypes)
        addCuisineTypes(cuisineTypes)
    }
}
