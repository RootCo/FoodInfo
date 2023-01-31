package com.example.foodinfo.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.foodinfo.local.dto.IngredientOfRecipeDB


@Entity(
    tableName = IngredientOfRecipeEntity.TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = RecipeEntity::class,
        parentColumns = arrayOf(RecipeEntity.Columns.ID),
        childColumns = arrayOf(IngredientOfRecipeEntity.Columns.RECIPE_ID),
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
    )]
)
data class IngredientOfRecipeEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Columns.ID)
    override val ID: Int = 0,

    @ColumnInfo(name = Columns.RECIPE_ID)
    override val recipeID: String,

    @ColumnInfo(name = Columns.TEXT)
    override val text: String,

    @ColumnInfo(name = Columns.QUANTITY)
    override val quantity: Float,

    @ColumnInfo(name = Columns.MEASURE)
    override val measure: String,

    @ColumnInfo(name = Columns.WEIGHT)
    override val weight: Float,

    @ColumnInfo(name = Columns.FOOD)
    override val food: String,

    @ColumnInfo(name = Columns.FOOD_CATEGORY)
    override val foodCategory: String,

    @ColumnInfo(name = Columns.FOOD_ID)
    override val foodID: String,

    @ColumnInfo(name = Columns.PREVIEW_URL)
    override val previewURL: String

) : IngredientOfRecipeDB(
    ID = ID,
    recipeID = recipeID,
    text = text,
    quantity = quantity,
    measure = measure,
    weight = weight,
    food = food,
    foodCategory = foodCategory,
    foodID = foodID,
    previewURL = previewURL
) {

    object Columns {
        const val ID = "id"
        const val RECIPE_ID = "recipe_id"
        const val TEXT = "text"
        const val QUANTITY = "quantity"
        const val MEASURE = "measure"
        const val WEIGHT = "weight"
        const val FOOD = "food"
        const val FOOD_CATEGORY = "food_category"
        const val FOOD_ID = "food_id"
        const val PREVIEW_URL = "preview_url"
    }

    companion object {
        const val TABLE_NAME = "ingredient_of_recipe"

        fun fromDB(item: IngredientOfRecipeDB): IngredientOfRecipeEntity {
            return IngredientOfRecipeEntity(
                ID = item.ID,
                recipeID = item.recipeID,
                text = item.text,
                quantity = item.quantity,
                measure = item.measure,
                weight = item.weight,
                food = item.food,
                foodCategory = item.foodCategory,
                foodID = item.foodID,
                previewURL = item.previewURL
            )
        }
    }
}