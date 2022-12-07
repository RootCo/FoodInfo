package com.example.foodinfo.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = RangeFieldEntity.TABLE_NAME)
data class RangeFieldEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Columns.ID)
    val id: Long = 0,

    @ColumnInfo(name = Columns.NAME)
    val name: String,

    @ColumnInfo(name = Columns.MEASURE)
    val measure: String,

    @ColumnInfo(name = Columns.CATEGORY)
    val category: String,

    @ColumnInfo(name = Columns.STEP)
    val step: Float,

    @ColumnInfo(name = Columns.MIN_VALUE)
    val minValue: Float,

    @ColumnInfo(name = Columns.MAX_VALUE)
    val maxValue: Float

) {

    object Columns {
        const val ID = "id"
        const val NAME = "name"
        const val MEASURE = "measure"
        const val CATEGORY = "category"
        const val STEP = "step"
        const val MIN_VALUE = "min_value"
        const val MAX_VALUE = "max_value"
    }

    companion object {
        const val TABLE_NAME = "range_fields"
    }
}
