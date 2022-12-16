package com.example.foodinfo.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
    tableName = BaseFieldFilterEntity.TABLE_NAME,
    indices = [Index(value = arrayOf(BaseFieldFilterEntity.Columns.NAME), unique = true)]
)
data class BaseFieldFilterEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Columns.ID)
    val id: Long = 0,

    @ColumnInfo(name = Columns.NAME)
    val name: String,

    @ColumnInfo(name = Columns.FILTER_NAME)
    val filterName: String,

    @ColumnInfo(name = Columns.MIN_VALUE)
    val minValue: Double,

    @ColumnInfo(name = Columns.MAX_VALUE)
    val maxValue: Double

) {
    object Columns {
        const val ID = "id"
        const val NAME = "name"
        const val FILTER_NAME = "filter_name"
        const val MIN_VALUE = "min_value"
        const val MAX_VALUE = "max_value"
    }

    companion object {
        const val TABLE_NAME = "filter_base_fields"
    }
}