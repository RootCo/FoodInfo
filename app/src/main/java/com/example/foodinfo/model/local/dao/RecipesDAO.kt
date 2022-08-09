package com.example.foodinfo.model.local.dao

import androidx.paging.PagingSource
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.foodinfo.model.local.entities.recipe.RecipeEntity
import com.example.foodinfo.model.local.entities.recipe.RecipeExtendedEntity
import com.example.foodinfo.model.local.entities.recipe.RecipeShortEntity
import com.example.foodinfo.model.local.entities.RecipeIngredientEntity
import com.example.foodinfo.model.local.entities.RecipeLabelEntity
import com.example.foodinfo.model.local.entities.RecipeNutrientEntity
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
            RecipeLabelEntity::class
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
