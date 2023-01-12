package com.example.foodinfo.local.pojo

import androidx.room.Embedded
import androidx.room.Relation
import com.example.foodinfo.local.entity.*


data class RecipeExtendedPOJO(

    @Embedded
    val recipe:RecipeEntity,

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
    val nutrients: List<NutrientRecipeEntity>,

    @Relation(
        parentColumn = RecipeEntity.Columns.ID,
        entityColumn = LabelRecipeEntity.Columns.RECIPE_ID
    )
    val labels: List<LabelRecipeEntity>
)