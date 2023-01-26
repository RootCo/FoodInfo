package com.example.foodinfo.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    tableName = LabelFilterEntity.TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = SearchFilterEntity::class,
        parentColumns = arrayOf(SearchFilterEntity.Columns.NAME),
        childColumns = arrayOf(LabelFilterEntity.Columns.FILTER_NAME),
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
    )]
)
data class LabelFilterEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Columns.ID)
    val id: Long = 0,

    @ColumnInfo(name = Columns.FILTER_NAME)
    val filterName: String,

    @ColumnInfo(name = Columns.CATEGORY)
    val category: String,

    @ColumnInfo(name = Columns.NAME)
    val name: String,

    @ColumnInfo(name = Columns.IS_SELECTED)
    val isSelected: Boolean

) {
    object Columns {
        const val ID = "id"
        const val FILTER_NAME = "filter_name"
        const val CATEGORY = "category"
        const val NAME = "name"
        const val IS_SELECTED = "is_selected"
    }

    companion object {
        const val TABLE_NAME = "filter_labels"
    }
}