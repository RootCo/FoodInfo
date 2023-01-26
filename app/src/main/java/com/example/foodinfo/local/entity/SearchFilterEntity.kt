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
    val name: String = DEFAULT_NAME
) {
    object Columns {
        const val ID = "id"
        const val NAME = "name"
    }

    companion object {
        const val TABLE_NAME = "filter_search"
        const val DEFAULT_NAME = "default_name"
    }
}