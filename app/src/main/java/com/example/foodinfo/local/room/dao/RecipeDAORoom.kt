package com.example.foodinfo.local.room.dao

import androidx.paging.PagingSource
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.foodinfo.local.dao.RecipeDAO
import com.example.foodinfo.local.dto.*
import com.example.foodinfo.local.room.entity.*
import com.example.foodinfo.local.room.pojo.*
import kotlinx.coroutines.flow.Flow


@Dao
interface RecipeDAORoom : RecipeDAO {
    @Transaction
    @Query(
        "SELECT * FROM ${RecipeEntity.TABLE_NAME} " +
                "WHERE ${RecipeEntity.Columns.ID} " +
                "IN (SELECT ${RecipeEntity.Columns.ID} " +
                "FROM ${RecipeEntity.TABLE_NAME} ORDER BY RANDOM())"
    )
    override fun getPopular(): PagingSource<Int, RecipeEntity>

    @Transaction
    @Query(
        "SELECT * FROM ${RecipeEntity.TABLE_NAME} " +
                "WHERE ${RecipeEntity.Columns.IS_FAVORITE} == 1"
    )
    override fun getFavorite(): PagingSource<Int, RecipeEntity>

    @Query(
        "SELECT ${RecipeEntity.Columns.ID} FROM ${RecipeEntity.TABLE_NAME} " +
                "WHERE ${RecipeEntity.Columns.IS_FAVORITE} == 1"
    )
    override fun getFavoriteIds(): List<String>

    @Transaction
    @RawQuery(
        observedEntities = [
            RecipeEntity::class,
            NutrientOfRecipeEntity::class,
            IngredientOfRecipeEntity::class,
            LabelOfRecipeEntity::class
        ]
    )
    override fun getByFilter(query: SupportSQLiteQuery): PagingSource<Int, RecipeEntity>

    @Transaction
    @Query(
        "SELECT * FROM ${RecipeEntity.TABLE_NAME} " +
                "WHERE ${RecipeEntity.Columns.ID} " +
                "LIKE '%' || :recipeID || '%'"
    )
    fun getByIdExtendedPOJO(recipeID: String): Flow<RecipeExtendedPOJO>

    override fun getByIdExtended(recipeID: String): Flow<RecipeExtendedDB> {
        return getByIdExtendedPOJO(recipeID)
    }

    @Query(
        "SELECT * FROM ${IngredientOfRecipeEntity.TABLE_NAME} " +
                "WHERE ${IngredientOfRecipeEntity.Columns.RECIPE_ID} " +
                "LIKE '%' || :recipeID || '%'"
    )
    fun getIngredientsEntity(recipeID: String): Flow<List<IngredientOfRecipeEntity>>

    override fun getIngredients(recipeID: String): Flow<List<IngredientOfRecipeDB>> {
        return getIngredientsEntity(recipeID)
    }

    @Transaction
    @Query(
        "SELECT * FROM ${NutrientOfRecipeEntity.TABLE_NAME} " +
                "WHERE ${NutrientOfRecipeEntity.Columns.RECIPE_ID} " +
                "LIKE '%' || :recipeID || '%'"
    )
    fun getNutrientsPOJO(recipeID: String): Flow<List<NutrientOfRecipeExtendedPOJO>>

    override fun getNutrients(recipeID: String): Flow<List<NutrientOfRecipeExtendedDB>> {
        return getNutrientsPOJO(recipeID)
    }

    @Query(
        "SELECT * FROM ${LabelOfRecipeEntity.TABLE_NAME} " +
                "WHERE ${LabelOfRecipeEntity.Columns.RECIPE_ID} " +
                "LIKE '%' || :recipeID || '%'"
    )
    fun getLabelsPOJO(recipeID: String): Flow<List<LabelOfRecipeExtendedPOJO>>

    override fun getLabels(recipeID: String): Flow<List<LabelOfRecipeExtendedDB>> {
        return getLabelsPOJO(recipeID)
    }


    @Query(
        "UPDATE ${RecipeEntity.TABLE_NAME} " +
                "SET ${RecipeEntity.Columns.IS_FAVORITE} = " +
                "NOT ${RecipeEntity.Columns.IS_FAVORITE} " +
                "WHERE ${RecipeEntity.Columns.ID}=:recipeID"
    )
    override fun invertFavoriteStatus(recipeID: String)

    @Query(
        "UPDATE ${RecipeEntity.TABLE_NAME} " +
                "SET ${RecipeEntity.Columns.IS_FAVORITE} = 0 " +
                "WHERE ${RecipeEntity.Columns.ID} IN (:recipeIDs)"
    )
    override fun delFromFavorite(recipeIDs: List<String>)

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
    override fun removeRecipes(recipeIds: List<String>)


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addRecipesEntity(recipes: List<RecipeEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addNutrientsEntity(nutrients: List<NutrientOfRecipeEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addIngredientsEntity(ingredients: List<IngredientOfRecipeEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addLabelsEntity(labels: List<LabelOfRecipeEntity>)

    /*
        when adding recipe from server into local DB there is way update it by id in case it's already exists,
        but because of auto generated ids for nutrients, ingredients and labels there is no way to check
        whether some of them was updated/removed or the new one was added on the server
        so if recipe already exists in local DB it's had to be removed and reinserted
     */
    @Transaction
    override fun addRecipes(
        recipes: List<RecipeDB>,
        nutrients: List<NutrientOfRecipeDB>,
        ingredients: List<IngredientOfRecipeDB>,
        labels: List<LabelOfRecipeDB>
    ) {
        // save favorite marks before deleting recipes
        val favoriteMarks = getFavoriteMarks(recipes.map { it.ID })

        // remove recipes that already in DB (foreign key will also delete nutrients etc.)
        removeRecipes(favoriteMarks.keys.toList())

        addRecipesEntity(recipes.map { RecipeEntity.fromDB(it) })
        addNutrientsEntity(nutrients.map { NutrientOfRecipeEntity.fromDB(it) })
        addIngredientsEntity(ingredients.map { IngredientOfRecipeEntity.fromDB(it) })
        addLabelsEntity(labels.map { LabelOfRecipeEntity.fromDB(it) })

        // change favorite mark for recipes that was marked as favorite before deleting
        addToFavorite(recipes.filter { it.isFavorite }.map { it.ID })
    }
}
