package com.example.foodinfo.local.room.pojo

import androidx.room.ColumnInfo
import androidx.room.Relation
import com.example.foodinfo.local.dto.RecipeExtendedDB
import com.example.foodinfo.local.room.entity.IngredientOfRecipeEntity
import com.example.foodinfo.local.room.entity.LabelOfRecipeEntity
import com.example.foodinfo.local.room.entity.NutrientOfRecipeEntity
import com.example.foodinfo.local.room.entity.RecipeEntity


data class RecipeExtendedPOJO(
    @ColumnInfo(name = RecipeEntity.Columns.ID)
    override val ID: String,

    @ColumnInfo(name = RecipeEntity.Columns.SOURCE)
    override val source: String,

    @ColumnInfo(name = RecipeEntity.Columns.NAME)
    override val name: String,

    @ColumnInfo(name = RecipeEntity.Columns.PREVIEW_URL)
    override val previewURL: String,

    @ColumnInfo(name = RecipeEntity.Columns.CALORIES)
    override val calories: Int,

    @ColumnInfo(name = RecipeEntity.Columns.INGREDIENTS_COUNT)
    override val ingredientsCount: Int,

    @ColumnInfo(name = RecipeEntity.Columns.WEIGHT)
    override val weight: Int,

    @ColumnInfo(name = RecipeEntity.Columns.COOKING_TIME)
    override val cookingTime: Int,

    @ColumnInfo(name = RecipeEntity.Columns.SERVINGS)
    override val servings: Int,

    @ColumnInfo(name = RecipeEntity.Columns.IS_FAVORITE)
    override val isFavorite: Boolean = false,

    @Relation(
        parentColumn = RecipeEntity.Columns.ID,
        entityColumn = IngredientOfRecipeEntity.Columns.RECIPE_ID
    )
    override val ingredients: List<IngredientOfRecipeEntity>,

    @Relation(
        parentColumn = RecipeEntity.Columns.ID,
        entityColumn = NutrientOfRecipeEntity.Columns.RECIPE_ID,
        entity = NutrientOfRecipeEntity::class
    )
    override val nutrients: List<NutrientOfRecipeExtendedPOJO>,

    @Relation(
        parentColumn = RecipeEntity.Columns.ID,
        entityColumn = LabelOfRecipeEntity.Columns.RECIPE_ID,
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
)