package com.example.foodinfo.local.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(
    tableName = BaseFieldFilterEntity.TABLE_NAME,
)
data class BaseFieldFilterEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Columns.ID)
    val id: Long = 0,

    @Embedded
    val fieldInfo: BaseFieldEntity,

    @ColumnInfo(name = Columns.FILTER_NAME)
    val filterName: String,

    @ColumnInfo(name = Columns.MIN_VALUE)
    val minValue: Float,

    @ColumnInfo(name = Columns.MAX_VALUE)
    val maxValue: Float

) {
    object Columns {
        const val ID = "id1"
        const val FILTER_NAME = "filter_name"
        const val MIN_VALUE = "min_value"
        const val MAX_VALUE = "max_value"
    }

    companion object {
        const val TABLE_NAME = "filter_base_fields"
    }
}