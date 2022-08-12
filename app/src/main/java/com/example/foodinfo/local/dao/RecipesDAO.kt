package com.example.foodinfo.local.dao

import androidx.paging.PagingSource
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.foodinfo.local.entity.RecipeIngredientEntity
import com.example.foodinfo.local.entity.RecipeLabelEntity
import com.example.foodinfo.local.entity.RecipeNutrientEntity
import com.example.foodinfo.local.entity.RecipeEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface RecipesDAO {
    @Query(
        "SELECT * FROM ${RecipeEntity.TABLE_NAME} " +
                "WHERE ${RecipeEntity.Columns.ID} " +
                "IN (SELECT ${RecipeEntity.Columns.ID} " +
                "FROM ${RecipeEntity.TABLE_NAME} ORDER BY RANDOM())"
    )
    fun getPopular(): PagingSource<Int, RecipeEntity>

    @RawQuery(
        observedEntities = [
            RecipeEntity::class,
            RecipeNutrientEntity::class,
            RecipeIngredientEntity::class,
            RecipeLabelEntity::class
        ]
    )
    fun getByFilter(query: SupportSQLiteQuery): PagingSource<Int, RecipeEntity>

    @Query(
        "SELECT * FROM ${RecipeEntity.TABLE_NAME} " +
                "WHERE ${RecipeEntity.Columns.ID} " +
                "LIKE '%' || :id || '%'"
    )
    fun getById(id: String): Flow<RecipeEntity>

    @Query(
        "SELECT * FROM ${RecipeIngredientEntity.TABLE_NAME} " +
                "WHERE ${RecipeIngredientEntity.Columns.RECIPE_ID} " +
                "LIKE '%' || :id || '%'"
    )
    fun getByIdIngredients(id: String): Flow<List<RecipeIngredientEntity>>

    @Query(
        "SELECT * FROM ${RecipeNutrientEntity.TABLE_NAME} " +
                "WHERE ${RecipeNutrientEntity.Columns.RECIPE_ID} " +
                "LIKE '%' || :id || '%'"
    )
    fun getByIdNutrients(id: String): Flow<List<RecipeNutrientEntity>>

    @Query(
        "SELECT * FROM ${RecipeLabelEntity.TABLE_NAME} " +
                "WHERE ${RecipeLabelEntity.Columns.RECIPE_ID} " +
                "LIKE '%' || :id || '%'"
    )
    fun getByIdLabels(id: String): Flow<List<RecipeLabelEntity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addRecipe(recipe: RecipeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addRecipes(recipes: List<RecipeEntity>)


    @Insert
    fun addNutrients(nutrients: List<RecipeNutrientEntity>)

    @Insert
    fun addIngredients(nutrients: List<RecipeIngredientEntity>)

    @Insert
    fun addLabels(dietLabels: List<RecipeLabelEntity>)

    @Transaction
    fun addAll(
        recipes: List<RecipeEntity>,
        nutrients: List<RecipeNutrientEntity>,
        ingredients: List<RecipeIngredientEntity>,
        labels: List<RecipeLabelEntity>
    ) {
        addRecipes(recipes)
        addNutrients(nutrients)
        addIngredients(ingredients)
        addLabels(labels)
    }
}
