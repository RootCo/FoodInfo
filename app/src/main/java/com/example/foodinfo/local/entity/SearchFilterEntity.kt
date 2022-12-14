package com.example.foodinfo.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
    tableName = SearchFilterEntity.TABLE_NAME,
    indices = [Index(value = arrayOf(SearchFilterEntity.Columns.NAME), unique = true)]
)
data class SearchFilterEntity(
    @ColumnInfo(name = Columns.ID)
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = Columns.NAME)
    val name: String,

    @ColumnInfo(name = Columns.INPUT_TEXT)
    val inputText: String = "",

    @ColumnInfo(name = Columns.RANGE_FIELDS)
    val rangeFields: String,

    @ColumnInfo(name = Columns.CATEGORY_FIELDS)
    val categoryFields: String,

    @ColumnInfo(name = Columns.NUTRIENT_FIELDS)
    val nutrientFields: String,
) {
    object Columns {
        const val ID = "id"
        const val NAME = "name"
        const val INPUT_TEXT = "input_text"
        const val RANGE_FIELDS = "range_fields"
        const val CATEGORY_FIELDS = "category_fields"
        const val NUTRIENT_FIELDS = "nutrient_fields"
    }

    companion object {
        const val TABLE_NAME = "filter_search"
        const val DEFAULT_FILTER_NAME = "filter_default"
    }
}