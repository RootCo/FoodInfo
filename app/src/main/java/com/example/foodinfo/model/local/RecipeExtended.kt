package com.example.foodinfo.model.local

import android.graphics.drawable.Drawable
import androidx.room.ColumnInfo
import androidx.room.Ignore
import androidx.room.Relation
import com.example.foodinfo.model.local.entities.Recipe
import com.example.foodinfo.model.local.entities.recipe_field.*


data class RecipeExtended(
    @ColumnInfo(name = Recipe.Columns.ID)
    val id: String,

    @ColumnInfo(name = Recipe.Columns.NAME)
    val name: String,

    @ColumnInfo(name = Recipe.Columns.PREVIEW_URL)
    val previewURL: String,

    @ColumnInfo(name = Recipe.Columns.CALORIES)
    val calories: Int,

    @ColumnInfo(name = Recipe.Columns.PROTEIN)
    val protein: Int,

    @ColumnInfo(name = Recipe.Columns.FAT)
    val fat: Int,

    @ColumnInfo(name = Recipe.Columns.CARB)
    val carb: Int,

    @ColumnInfo(name = Recipe.Columns.SOURCE)
    val source: String,

    @ColumnInfo(name = Recipe.Columns.TOTAL_WEIGHT)
    val totalWeight: Int,

    @ColumnInfo(name = Recipe.Columns.TOTAL_TIME)
    val totalTime: Int,

    @ColumnInfo(name = Recipe.Columns.SERVINGS)
    val servings: Int,

    @Relation(
        parentColumn = Recipe.Columns.ID,
        entityColumn = MealType.Columns.RECIPE_ID
    )
    val mealType: List<MealType>,

    @Relation(
        parentColumn = Recipe.Columns.ID,
        entityColumn = DishType.Columns.RECIPE_ID
    )
    val dishType: List<DishType>,

    @Relation(
        parentColumn = Recipe.Columns.ID,
        entityColumn = DietType.Columns.RECIPE_ID
    )
    val dietType: List<DietType>,

    @Relation(
        parentColumn = Recipe.Columns.ID,
        entityColumn = HealthType.Columns.RECIPE_ID
    )
    val healthType: List<HealthType>,

    @Relation(
        parentColumn = Recipe.Columns.ID,
        entityColumn = CuisineType.Columns.RECIPE_ID
    )
    val cuisineType: List<CuisineType>,

    @Relation(
        parentColumn = Recipe.Columns.ID,
        entityColumn = Ingredient.Columns.RECIPE_ID
    )
    val ingredients: List<Ingredient>,

    @Relation(
        parentColumn = Recipe.Columns.ID,
        entityColumn = Nutrient.Columns.RECIPE_ID
    )
    val totalNutrients: List<Nutrient>,

    @Relation(
        parentColumn = Recipe.Columns.ID,
        entityColumn = Nutrient.Columns.RECIPE_ID
    )
    val totalDaily: List<Nutrient>
) {
    @Ignore
    var preview: Drawable? = null

    companion object {
        const val SELECTOR = "SELECT * FROM ${Recipe.TABLE_NAME}"
    }
}