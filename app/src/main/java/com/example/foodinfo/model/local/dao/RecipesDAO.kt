package com.example.foodinfo.model.local.dao

import androidx.paging.PagingSource
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.foodinfo.model.local.RecipeExtendedDTO
import com.example.foodinfo.model.local.RecipeShortDTO
import com.example.foodinfo.model.local.entities.Recipe
import com.example.foodinfo.model.local.entities.recipe_field.*
import kotlinx.coroutines.flow.Flow


@Dao
interface RecipesDAO {
    @Query(
        "SELECT * FROM ${Recipe.TABLE_NAME} " +
                "WHERE ${Recipe.Columns.ID} " +
                "IN (SELECT ${Recipe.Columns.ID} " +
                "FROM ${Recipe.TABLE_NAME} ORDER BY RANDOM())"
    )
    fun getPopular(): PagingSource<Int, RecipeShortDTO>

    @RawQuery(
        observedEntities = [
            Recipe::class,
            Nutrient::class,
            Ingredient::class,
            DietType::class,
            DishType::class,
            MealType::class,
            HealthType::class,
            CuisineType::class,
            CuisineType::class,
        ]
    )
    fun getByFilterShort(query: SupportSQLiteQuery): PagingSource<Int, RecipeShortDTO>

    @Query(
        "${RecipeExtendedDTO.SELECTOR} " +
                "WHERE ${Recipe.Columns.ID} " +
                "LIKE '%' || :id || '%'"
    )
    fun getByIdExtended(id: String): Flow<RecipeExtendedDTO>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addRecipe(recipe: Recipe)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
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
