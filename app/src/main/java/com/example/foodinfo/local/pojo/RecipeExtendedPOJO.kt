package com.example.foodinfo.local.pojo

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.foodinfo.local.entity.*


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

    @ColumnInfo(name = RecipeEntity.Columns.TOTAL_INGREDIENTS)
    val totalIngredients: Int,

    @ColumnInfo(name = RecipeEntity.Columns.TOTAL_WEIGHT)
    val totalWeight: Int,

    @ColumnInfo(name = RecipeEntity.Columns.TOTAL_TIME)
    val totalTime: Int,

    @ColumnInfo(name = RecipeEntity.Columns.SERVINGS)
    val servings: Int,

    @Relation(
        parentColumn = RecipeEntity.Columns.ID,
        entityColumn = FavoriteMarkEntity.Columns.RECIPE_ID
    )
    val favoriteMark: FavoriteMarkEntity,

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