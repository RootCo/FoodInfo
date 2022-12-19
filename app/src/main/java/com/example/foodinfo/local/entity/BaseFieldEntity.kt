package com.example.foodinfo.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
    tableName = BaseFieldEntity.TABLE_NAME,
    indices = [Index(value = arrayOf(BaseFieldEntity.Columns.NAME), unique = true)]
)
data class BaseFieldEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Columns.ID)
    val id: Long = 0,

    @ColumnInfo(name = Columns.NAME)
    val name: String,

    @ColumnInfo(name = Columns.MEASURE)
    val measure: String,

    @ColumnInfo(name = Columns.RANGE_MIN)
    val rangeMin: Float,

    @ColumnInfo(name = Columns.RANGE_MAX)
    val rangeMax: Float,

    @ColumnInfo(name = Columns.STEP_SIZE)
    val stepSize: Float

) {
    object Columns {
        const val ID = "id"
        const val NAME = "name"
        const val MEASURE = "measure"
        const val RANGE_MIN = "range_min"
        const val RANGE_MAX = "range_max"
        const val STEP_SIZE = "step_size"
    }

    companion object {
        const val TABLE_NAME = "base_fields"
    }
}