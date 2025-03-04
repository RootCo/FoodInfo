package com.example.foodinfo.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.foodinfo.local.dto.NutrientRecipeAttrDB


@Entity(
    tableName = NutrientRecipeAttrDB.TABLE_NAME,
    indices = [Index(value = arrayOf(NutrientRecipeAttrDB.Columns.ID), unique = true)]
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

    companion object {
        fun toEntity(item: NutrientRecipeAttrDB): NutrientRecipeAttrEntity {
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