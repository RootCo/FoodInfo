package com.example.foodinfo.local.dao

import androidx.paging.PagingSource
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.foodinfo.local.entity.*
import com.example.foodinfo.local.pojo.RecipeExtendedPOJO
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
    fun getPopular(): PagingSource<Int, RecipeEntity>

    @Transaction
    @Query(
        "SELECT * FROM ${RecipeEntity.TABLE_NAME} " +
                "WHERE ${RecipeEntity.Columns.IS_FAVORITE} == 1"
    )
    fun getFavorite(): PagingSource<Int, RecipeEntity>

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
            LabelRecipeEntity::class,
        ]
    )
    fun getByFilter(query: SupportSQLiteQuery): PagingSource<Int, RecipeEntity>

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
    fun getByIdNutrients(id: String): Flow<List<NutrientRecipeEntity>>

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
                "WHERE ${RecipeEntity.Columns.ID} = :id"
    )
    fun updateFavoriteStatus(id: String)

    @Query(
        "UPDATE ${RecipeEntity.TABLE_NAME} " +
                "SET ${RecipeEntity.Columns.IS_FAVORITE} = 0 " +
                "WHERE ${RecipeEntity.Columns.ID} IN (:id)"
    )
    fun delFromFavorite(id: List<String>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addRecipe(recipe: RecipeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addRecipes(recipes: List<RecipeEntity>)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNutrients(nutrients: List<NutrientRecipeEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addIngredients(nutrients: List<RecipeIngredientEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addLabels(dietLabels: List<LabelRecipeEntity>)

    @Transaction
    fun addAll(
        recipes: List<RecipeEntity>,
        nutrients: List<NutrientRecipeEntity>,
        ingredients: List<RecipeIngredientEntity>,
        labels: List<LabelRecipeEntity>,
    ) {
        addRecipes(recipes)
        addNutrients(nutrients)
        addIngredients(ingredients)
        addLabels(labels)
    }
}
