package com.example.foodinfo.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
    tableName = NutrientFieldEntity.TABLE_NAME,
    indices = [Index(value = arrayOf(NutrientFieldEntity.Columns.NAME), unique = true)]
)
data class NutrientFieldEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Columns.ID)
    val id: Long = 0,

    @ColumnInfo(name = Columns.NAME)
    val name: String,

    @ColumnInfo(name = Columns.TAG)
    val tag: String,

    @ColumnInfo(name = Columns.MEASURE)
    val measure: String,

    @ColumnInfo(name = Columns.RANGE_MIN)
    val rangeMin: Double,

    @ColumnInfo(name = Columns.RANGE_MAX)
    val rangeMax: Double,

    @ColumnInfo(name = Columns.STEP_SIZE)
    val stepSize: Double,

    @ColumnInfo(name = Columns.DAILY_ALLOWANCE)
    val dailyAllowance: Double,

    @ColumnInfo(name = Columns.DESCRIPTION)
    val description: String,

    @ColumnInfo(name = Columns.PREVIEW_URL)
    val previewURL: String,

    ) {
    object Columns {
        const val ID = "id"
        const val NAME = "name"
        const val TAG = "tag"
        const val MEASURE = "measure"
        const val RANGE_MIN = "range_min"
        const val RANGE_MAX = "range_max"
        const val STEP_SIZE = "step_size"
        const val DAILY_ALLOWANCE = "daily_allowance"
        const val DESCRIPTION = "description"
        const val PREVIEW_URL = "preview_url"
    }

    companion object {
        const val TABLE_NAME = "nutrients_field"
    }
}