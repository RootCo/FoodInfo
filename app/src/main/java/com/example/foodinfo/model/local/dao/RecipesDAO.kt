package com.example.foodinfo.model.local.dao

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.foodinfo.model.local.RecipeFull
import com.example.foodinfo.model.local.RecipeShort
import com.example.foodinfo.model.local.entities.Recipe
import com.example.foodinfo.model.local.entities.recipe_field.*


@Dao
interface RecipesDAO {
    @Query(
        "SELECT * FROM ${Recipe.TABLE_NAME} " +
                "WHERE ${Recipe.Columns.ID} " +
                "IN (SELECT ${Recipe.Columns.ID} " +
                "FROM ${Recipe.TABLE_NAME} ORDER BY RANDOM() LIMIT 10)"
    )
    fun getDaily(): List<RecipeShort>

    @Query("SELECT * FROM ${Recipe.TABLE_NAME} WHERE ${Recipe.Columns.ID} == :id")
    fun getById(id: String): RecipeShort

    @RawQuery
    fun getByFilterShort(query: SupportSQLiteQuery): List<RecipeShort>

    @Transaction
    @RawQuery
    fun getByFilterFull(query: SupportSQLiteQuery): List<RecipeFull>


    @Insert
    fun addRecipe(recipe: Recipe)

    @Insert
    fun addRecipes(recipes: List<Recipe>)

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
