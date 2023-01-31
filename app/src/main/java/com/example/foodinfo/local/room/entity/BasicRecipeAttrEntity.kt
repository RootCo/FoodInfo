package com.example.foodinfo.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.foodinfo.local.dto.BasicRecipeAttrDB


@Entity(
    tableName = BasicRecipeAttrEntity.TABLE_NAME,
    indices = [Index(value = arrayOf(BasicRecipeAttrEntity.Columns.ID), unique = true)]
)
data class BasicRecipeAttrEntity(
    @PrimaryKey
    @ColumnInfo(name = Columns.ID)
    override val ID: Int = 0,

    @ColumnInfo(name = Columns.TAG)
    override val tag: String?,

    @ColumnInfo(name = Columns.NAME)
    override val name: String,

    @ColumnInfo(name = Columns.COLUMN_NAME)
    override val columnName: String,

    @ColumnInfo(name = Columns.MEASURE)
    override val measure: String,

    @ColumnInfo(name = Columns.RANGE_MIN)
    override val rangeMin: Float,

    @ColumnInfo(name = Columns.RANGE_MAX)
    override val rangeMax: Float,

    @ColumnInfo(name = Columns.STEP_SIZE)
    override val stepSize: Float

) : BasicRecipeAttrDB(
    ID = ID,
    tag = tag,
    name = name,
    columnName = columnName,
    measure = measure,
    rangeMin = rangeMin,
    rangeMax = rangeMax,
    stepSize = stepSize
) {

    object Columns {
        const val ID = "id"
        const val TAG = "tag"
        const val NAME = "name"
        const val COLUMN_NAME = "column_name"
        const val MEASURE = "measure"
        const val RANGE_MIN = "range_min"
        const val RANGE_MAX = "range_max"
        const val STEP_SIZE = "step_size"
    }

    companion object {
        const val TABLE_NAME = "basic_recipe_attr"

        fun fromDB(item: BasicRecipeAttrDB): BasicRecipeAttrEntity {
            return BasicRecipeAttrEntity(
                ID = item.ID,
                tag = item.tag,
                name = item.name,
                columnName = item.columnName,
                measure = item.measure,
                rangeMin = item.rangeMin,
                rangeMax = item.rangeMax,
                stepSize = item.stepSize
            )
        }
    }
}