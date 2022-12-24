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
    @Query(
        "SELECT * FROM ${RecipeEntity.TABLE_NAME} " +
                "WHERE ${RecipeEntity.Columns.ID} " +
                "IN (SELECT ${RecipeEntity.Columns.ID} " +
                "FROM ${RecipeEntity.TABLE_NAME} ORDER BY RANDOM())"
    )
    fun getPopular(): PagingSource<Int, RecipePOJO>

    @Query(
        "SELECT * FROM ${RecipeEntity.TABLE_NAME} " +
                "WHERE ${RecipeEntity.Columns.ID} " +
                "IN (SELECT ${FavoriteMarkEntity.Columns.RECIPE_ID} " +
                "FROM ${FavoriteMarkEntity.TABLE_NAME} " +
                "WHERE ${FavoriteMarkEntity.Columns.IS_FAVORITE} == 1)"
    )
    fun getFavorite(): PagingSource<Int, RecipePOJO>

    @Query(
        "SELECT ${RecipeEntity.Columns.ID} FROM ${RecipeEntity.TABLE_NAME} " +
                "WHERE ${RecipeEntity.Columns.ID} " +
                "IN (SELECT ${FavoriteMarkEntity.Columns.RECIPE_ID} " +
                "FROM ${FavoriteMarkEntity.TABLE_NAME} " +
                "WHERE ${FavoriteMarkEntity.Columns.IS_FAVORITE} == 1)"
    )
    fun getFavoriteIds(): List<String>

    @RawQuery(
        observedEntities = [
            RecipeEntity::class,
            NutrientRecipeEntity::class,
            RecipeIngredientEntity::class,
            LabelRecipeEntity::class,
            FavoriteMarkEntity::class
        ]
    )
    fun getByFilter(query: SupportSQLiteQuery): PagingSource<Int, RecipePOJO>

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
        "UPDATE ${FavoriteMarkEntity.TABLE_NAME} " +
                "SET ${FavoriteMarkEntity.Columns.IS_FAVORITE} = " +
                "NOT ${FavoriteMarkEntity.Columns.IS_FAVORITE} " +
                "WHERE ${FavoriteMarkEntity.Columns.RECIPE_ID}=:recipeId"
    )
    fun updateFavoriteStatus(recipeId: String)

    @Query(
        "UPDATE ${FavoriteMarkEntity.TABLE_NAME} " +
                "SET ${FavoriteMarkEntity.Columns.IS_FAVORITE} = 0 " +
                "WHERE ${FavoriteMarkEntity.Columns.RECIPE_ID} IN (:recipeId)"
    )
    fun delFromFavorite(recipeId: List<String>)

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

    /*
        При обновлении рецепта из сети создается объект RecipeEntity с isFavorite = false
        поэтому тут без OnConflictStrategy.REPLACE чтобы избежать ситуаций, когда
        рецепт был добавлен в закладки, потом рецепт обновился на сервере и isFavorite
        поле изменилось на false. Для добавления/удаления рецепта из закладок
        есть отдельный метод: changeFavoriteStatus()
     */
    @Insert
    fun addFavoriteMarks(favoriteMarks: List<FavoriteMarkEntity>)

    @Transaction
    fun addAll(
        recipes: List<RecipeEntity>,
        nutrients: List<NutrientRecipeEntity>,
        ingredients: List<RecipeIngredientEntity>,
        labels: List<LabelRecipeEntity>,
        favoriteMarks: List<FavoriteMarkEntity>
    ) {
        addRecipes(recipes)
        addNutrients(nutrients)
        addIngredients(ingredients)
        addLabels(labels)
        addFavoriteMarks(favoriteMarks)
    }
}
