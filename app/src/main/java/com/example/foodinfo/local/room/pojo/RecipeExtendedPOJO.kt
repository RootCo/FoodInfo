package com.example.foodinfo.local.room.pojo

import androidx.room.ColumnInfo
import androidx.room.Relation
import com.example.foodinfo.local.dto.*
import com.example.foodinfo.local.room.entity.IngredientOfRecipeEntity
import com.example.foodinfo.local.room.entity.LabelOfRecipeEntity
import com.example.foodinfo.local.room.entity.NutrientOfRecipeEntity
import com.example.foodinfo.local.room.entity.RecipeEntity


data class RecipeExtendedPOJO(
    @ColumnInfo(name = RecipeDB.Columns.ID)
    override val ID: String,

    @ColumnInfo(name = RecipeDB.Columns.SOURCE)
    override val source: String,

    @ColumnInfo(name = RecipeDB.Columns.NAME)
    override val name: String,

    @ColumnInfo(name = RecipeDB.Columns.PREVIEW_URL)
    override val previewURL: String,

    @ColumnInfo(name = RecipeDB.Columns.CALORIES)
    override val calories: Int,

    @ColumnInfo(name = RecipeDB.Columns.INGREDIENTS_COUNT)
    override val ingredientsCount: Int,

    @ColumnInfo(name = RecipeDB.Columns.WEIGHT)
    override val weight: Int,

    @ColumnInfo(name = RecipeDB.Columns.COOKING_TIME)
    override val cookingTime: Int,

    @ColumnInfo(name = RecipeDB.Columns.SERVINGS)
    override val servings: Int,

    @ColumnInfo(name = RecipeDB.Columns.IS_FAVORITE)
    override val isFavorite: Boolean = false,

    @Relation(
        parentColumn = RecipeDB.Columns.ID,
        entityColumn = IngredientOfRecipeDB.Columns.RECIPE_ID
    )
    override val ingredients: List<IngredientOfRecipeEntity>,

    @Relation(
        parentColumn = RecipeDB.Columns.ID,
        entityColumn = NutrientOfRecipeDB.Columns.RECIPE_ID,
        entity = NutrientOfRecipeEntity::class
    )
    override val nutrients: List<NutrientOfRecipeExtendedPOJO>,

    @Relation(
        parentColumn = RecipeDB.Columns.ID,
        entityColumn = LabelOfRecipeDB.Columns.RECIPE_ID,
        entity = LabelOfRecipeEntity::class
    )
    override val labels: List<LabelOfRecipeExtendedPOJO>

) : RecipeExtendedDB(
    ID = ID,
    source = source,
    name = name,
    previewURL = previewURL,
    calories = calories,
    ingredientsCount = ingredientsCount,
    weight = weight,
    cookingTime = cookingTime,
    servings = servings,
    isFavorite = isFavorite,
    ingredients = ingredients,
    nutrients = nutrients,
    labels = labels
) {

    companion object {
        fun toEntity(item: RecipeExtendedDB): RecipeEntity {
            return RecipeEntity(
                ID = item.ID,
                source = item.source,
                name = item.name,
                previewURL = item.previewURL,
                calories = item.calories,
                ingredientsCount = item.ingredientsCount,
                weight = item.weight,
                cookingTime = item.cookingTime,
                servings = item.servings,
                isFavorite = item.isFavorite
            )
        }
    }
}