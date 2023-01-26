package com.example.foodinfo.local.pojo

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.foodinfo.local.entity.LabelRecipeEntity
import com.example.foodinfo.local.entity.NutrientRecipeEntity
import com.example.foodinfo.local.entity.RecipeEntity
import com.example.foodinfo.local.entity.RecipeIngredientEntity


data class RecipeExtendedPOJO(
    @PrimaryKey
    @ColumnInfo(name = RecipeEntity.Columns.ID)
    val id: String,

    @ColumnInfo(name = RecipeEntity.Columns.SOURCE)
    val source: String,

    @ColumnInfo(name = RecipeEntity.Columns.NAME)
    val name: String,

    @ColumnInfo(name = RecipeEntity.Columns.PREVIEW_URL)
    val previewURL: String,

    @ColumnInfo(name = RecipeEntity.Columns.CALORIES)
    val calories: Int,

    @ColumnInfo(name = RecipeEntity.Columns.INGREDIENTS_COUNT)
    val ingredientsCount: Int,

    @ColumnInfo(name = RecipeEntity.Columns.WEIGHT)
    val weight: Int,

    @ColumnInfo(name = RecipeEntity.Columns.COOKING_TIME)
    val cookingTime: Int,

    @ColumnInfo(name = RecipeEntity.Columns.SERVINGS)
    val servings: Int,

    @ColumnInfo(name = RecipeEntity.Columns.IS_FAVORITE)
    val isFavorite: Boolean,

    @Relation(
        parentColumn = RecipeEntity.Columns.ID,
        entityColumn = RecipeIngredientEntity.Columns.RECIPE_ID
    )
    val ingredients: List<RecipeIngredientEntity>,

    /*
        Using entity = NutrientRecipeEntity::class here was not obvious but as docs says
        https://developer.android.com/reference/android/arch/persistence/room/Relation

        "The type of the field annotated with Relation must be a List or Set.
        By default, the Entity type is inferred from the return type.
        If you would like to return a different object,
        you can specify the entity() property in the annotation."
     */
    @Relation(
        parentColumn = RecipeEntity.Columns.ID,
        entityColumn = NutrientRecipeEntity.Columns.RECIPE_ID,
        entity = NutrientRecipeEntity::class
    )
    val nutrients: List<NutrientRecipePOJO>,

    @Relation(
        parentColumn = RecipeEntity.Columns.ID,
        entityColumn = LabelRecipeEntity.Columns.RECIPE_ID
    )
    val labels: List<LabelRecipeEntity>
)