package com.example.foodinfo.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.foodinfo.local.dto.NutrientRecipeAttrDB


@Entity(
    tableName = NutrientRecipeAttrEntity.TABLE_NAME,
    indices = [Index(value = arrayOf(NutrientRecipeAttrEntity.Columns.ID), unique = true)]
)
data class NutrientRecipeAttrEntity(
    @PrimaryKey
    @ColumnInfo(name = Columns.ID)
    override val ID: Int,

    @ColumnInfo(name = Columns.TAG)
    override val tag: String,

    @ColumnInfo(name = Columns.NAME)
    override val name: String,

    @ColumnInfo(name = Columns.MEASURE)
    override val measure: String,

    @ColumnInfo(name = Columns.DESCRIPTION)
    override val description: String,

    @ColumnInfo(name = Columns.HAS_RDI)
    override val hasRDI: Boolean,

    @ColumnInfo(name = Columns.PREVIEW_URL)
    override val previewURL: String,

    @ColumnInfo(name = Columns.DAILY_ALLOWANCE)
    override val dailyAllowance: Float,

    @ColumnInfo(name = Columns.STEP_SIZE)
    override val stepSize: Float,

    @ColumnInfo(name = Columns.RANGE_MIN)
    override val rangeMin: Float,

    @ColumnInfo(name = Columns.RANGE_MAX)
    override val rangeMax: Float

) : NutrientRecipeAttrDB(
    ID = ID,
    tag = tag,
    name = name,
    measure = measure,
    description = description,
    hasRDI = hasRDI,
    previewURL = previewURL,
    dailyAllowance = dailyAllowance,
    stepSize = stepSize,
    rangeMin = rangeMin,
    rangeMax = rangeMax
) {

    object Columns {
        const val ID = "id"
        const val TAG = "tag"
        const val NAME = "name"
        const val MEASURE = "measure"
        const val DESCRIPTION = "description"
        const val PREVIEW_URL = "preview_url"
        const val HAS_RDI = "has_rdi"
        const val DAILY_ALLOWANCE = "daily_allowance"
        const val STEP_SIZE = "step_size"
        const val RANGE_MIN = "range_min"
        const val RANGE_MAX = "range_max"
    }

    companion object {
        const val TABLE_NAME = "nutrient_recipe_attr"

        fun fromDB(item: NutrientRecipeAttrDB): NutrientRecipeAttrEntity {
            return NutrientRecipeAttrEntity(
                ID = item.ID,
                tag = item.tag,
                name = item.name,
                measure = item.measure,
                description = item.description,
                hasRDI = item.hasRDI,
                previewURL = item.previewURL,
                dailyAllowance = item.dailyAllowance,
                stepSize = item.stepSize,
                rangeMin = item.rangeMin,
                rangeMax = item.rangeMax
            )
        }
    }
}