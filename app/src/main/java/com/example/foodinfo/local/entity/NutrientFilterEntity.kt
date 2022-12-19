package com.example.foodinfo.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    tableName = NutrientFilterEntity.TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = SearchFilterEntity::class,
        parentColumns = arrayOf(SearchFilterEntity.Columns.NAME),
        childColumns = arrayOf(NutrientFilterEntity.Columns.FILTER_NAME),
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
    )]
)
data class NutrientFilterEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Columns.ID)
    val id: Long = 0,

    @ColumnInfo(name = Columns.FILTER_NAME)
    val filterName: String,

    @ColumnInfo(name = Columns.NAME)
    val name: String,

    @ColumnInfo(name = Columns.MIN_VALUE)
    val minValue: Float,

    @ColumnInfo(name = Columns.MAX_VALUE)
    val maxValue: Float
) {
    object Columns {
        const val ID = "id"
        const val FILTER_NAME = "nutrient_filter_name"
        const val NAME = "name"
        const val MIN_VALUE = "min_value"
        const val MAX_VALUE = "max_value"
    }

    companion object {
        const val TABLE_NAME = "filter_nutrients"
    }
}