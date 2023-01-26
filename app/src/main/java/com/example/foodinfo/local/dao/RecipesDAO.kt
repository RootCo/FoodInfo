package com.example.foodinfo.local.dao

import androidx.paging.PagingSource
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.foodinfo.local.entity.*
import com.example.foodinfo.local.pojo.NutrientRecipePOJO
import com.example.foodinfo.local.pojo.RecipeExtendedPOJO
import com.example.foodinfo.local.pojo.RecipePOJO
import kotlinx.coroutines.flow.Flow


@Dao
interface RecipesDAO {
    @Transaction
    @Query(
        "SELECT * FROM ${RecipeEntity.TABLE_NAME} " +
                "WHERE ${RecipeEntity.Columns.ID} " +
                "IN (SELECT ${RecipeEntity.Columns.ID} " +
                "FROM ${RecipeEntity.TABLE_NAME} ORDER BY RANDOM())"
    )
    fun getPopular(): PagingSource<Int, RecipePOJO>

    @Transaction
    @Query(
        "SELECT * FROM ${RecipeEntity.TABLE_NAME} " +
                "WHERE ${RecipeEntity.Columns.IS_FAVORITE} == 1"
    )
    fun getFavorite(): PagingSource<Int, RecipePOJO>

    @Query(
        "SELECT ${RecipeEntity.Columns.ID} FROM ${RecipeEntity.TABLE_NAME} " +
                "WHERE ${RecipeEntity.Columns.IS_FAVORITE} == 1"
    )
    fun getFavoriteIds(): List<String>

    @Transaction
    @RawQuery(
        observedEntities = [
            RecipeEntity::class,
            NutrientRecipeEntity::class,
            RecipeIngredientEntity::class,
            LabelRecipeEntity::class
        ]
    )
    fun getByFilter(query: SupportSQLiteQuery): PagingSource<Int, RecipePOJO>

    @Transaction
    @Query(
        "SELECT * FROM ${RecipeEntity.TABLE_NAME} " +
                "WHERE ${RecipeEntity.Columns.ID} " +
                "LIKE '%' || :id || '%'"
    )
    fun getByIdExtended(id: String): Flow<RecipeExtendedPOJO>

    @Query(
        "SELECT * FROM ${RecipeIngredientEntity.TABLE_NAME} " +
                "WHERE ${RecipeIngredientEntity.Columns.RECIPE_ID} " +
                "LIKE '%' || :id || '%'"
    )
    fun getByIdIngredients(id: String): Flow<List<RecipeIngredientEntity>>

    @Transaction
    @Query(
        "SELECT * FROM ${NutrientRecipeEntity.TABLE_NAME} " +
                "WHERE ${NutrientRecipeEntity.Columns.RECIPE_ID} " +
                "LIKE '%' || :id || '%'"
    )
    fun getByIdNutrients(id: String): Flow<List<NutrientRecipePOJO>>

    @Query(
        "SELECT * FROM ${LabelRecipeEntity.TABLE_NAME} " +
                "WHERE ${LabelRecipeEntity.Columns.RECIPE_ID} " +
                "LIKE '%' || :id || '%'"
    )
    fun getByIdLabels(id: String): Flow<List<LabelRecipeEntity>>


    @Query(
        "UPDATE ${RecipeEntity.TABLE_NAME} " +
                "SET ${RecipeEntity.Columns.IS_FAVORITE} = " +
                "NOT ${RecipeEntity.Columns.IS_FAVORITE} " +
                "WHERE ${RecipeEntity.Columns.ID}=:recipeID"
    )
    fun invertFavoriteStatus(recipeID: String)

    @Query(
        "UPDATE ${RecipeEntity.TABLE_NAME} " +
                "SET ${RecipeEntity.Columns.IS_FAVORITE} = 0 " +
                "WHERE ${RecipeEntity.Columns.ID} IN (:recipeIDs)"
    )
    fun delFromFavorite(recipeIDs: List<String>)

    @Query(
        "UPDATE ${RecipeEntity.TABLE_NAME} " +
                "SET ${RecipeEntity.Columns.IS_FAVORITE} = 1 " +
                "WHERE ${RecipeEntity.Columns.ID} IN (:recipeIds)"
    )
    fun addToFavorite(recipeIds: List<String>)

    @MapInfo(keyColumn = RecipeEntity.Columns.ID, valueColumn = RecipeEntity.Columns.IS_FAVORITE)
    @Query(
        "SELECT ${RecipeEntity.Columns.ID}, ${RecipeEntity.Columns.IS_FAVORITE} " +
                "FROM ${RecipeEntity.TABLE_NAME} " +
                "WHERE ${RecipeEntity.Columns.ID} IN (:recipeIds)"
    )
    fun getFavoriteMarks(recipeIds: List<String>): Map<String, Boolean>


    @Query(
        "DELETE FROM ${RecipeEntity.TABLE_NAME} " +
                "WHERE ${RecipeEntity.Columns.ID} IN (:recipeIds)"
    )
    fun removeRecipes(recipeIds: List<String>)


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addRecipes(recipes: List<RecipeEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addNutrients(nutrients: List<NutrientRecipeEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addIngredients(nutrients: List<RecipeIngredientEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addLabels(dietLabels: List<LabelRecipeEntity>)

    /*
        when adding recipe from server into local DB there is way update it by id in case it's already exists,
        but because of auto generated ids for nutrients, ingredients and labels there is no way to check
        whether some of them was updated/removed or the new one was added on the server
        so if recipe already exists in local DB it's had to be removed and reinserted
     */
    @Transaction
    fun addAll(
        recipes: List<RecipeEntity>,
        nutrients: List<NutrientRecipeEntity>,
        ingredients: List<RecipeIngredientEntity>,
        labels: List<LabelRecipeEntity>
    ) {
        // save favorite marks before deleting recipes
        val favoriteMarks = getFavoriteMarks(recipes.map { it.id })

        // remove recipes that already in DB (foreign key will also delete nutrients etc.)
        removeRecipes(favoriteMarks.keys.toList())

        addRecipes(recipes)
        addNutrients(nutrients)
        addIngredients(ingredients)
        addLabels(labels)

        // change favorite mark for recipes that was marked as favorite before deleting
        addToFavorite(recipes.filter { it.isFavorite }.map { it.id })
    }
}
