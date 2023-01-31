package com.example.foodinfo.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.foodinfo.local.dto.NutrientOfRecipeDB


@Entity(
    tableName = NutrientOfRecipeEntity.TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = RecipeEntity::class,
        parentColumns = arrayOf(RecipeEntity.Columns.ID),
        childColumns = arrayOf(NutrientOfRecipeEntity.Columns.RECIPE_ID),
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
    )]
)
data class NutrientOfRecipeEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Columns.ID)
    override val ID: Int = 0,

    @ColumnInfo(name = Columns.RECIPE_ID)
    override val recipeID: String,

    @ColumnInfo(name = Columns.INFO_ID)
    override val infoID: Int,

    @ColumnInfo(name = Columns.TOTAL_VALUE)
    override val value: Float

) : NutrientOfRecipeDB(
    ID = ID,
    recipeID = recipeID,
    infoID = infoID,
    value = value
) {

    object Columns {
        const val ID = "id"
        const val RECIPE_ID = "recipe_id"
        const val INFO_ID = "info_id"
        const val TOTAL_VALUE = "total_value"
    }

    companion object {
        const val TABLE_NAME = "nutrient_of_recipe"

        fun fromDB(item: NutrientOfRecipeDB): NutrientOfRecipeEntity {
            return NutrientOfRecipeEntity(
                ID = item.ID,
                recipeID = item.recipeID,
                infoID = item.infoID,
                value = item.value
            )
        }
    }
}