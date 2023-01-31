package com.example.foodinfo.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.foodinfo.local.dto.BasicOfSearchFilterDB


@Entity(
    tableName = BasicOfSearchFilterEntity.TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = SearchFilterEntity::class,
        parentColumns = arrayOf(SearchFilterEntity.Columns.NAME),
        childColumns = arrayOf(BasicOfSearchFilterEntity.Columns.FILTER_NAME),
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
    )]
)
data class BasicOfSearchFilterEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Columns.ID)
    override val ID: Int = 0,

    @ColumnInfo(name = Columns.INFO_ID)
    override val infoID: Int,

    @ColumnInfo(name = Columns.FILTER_NAME)
    override val filterName: String,

    @ColumnInfo(name = Columns.MIN_VALUE)
    override val minValue: Float,

    @ColumnInfo(name = Columns.MAX_VALUE)
    override val maxValue: Float

) : BasicOfSearchFilterDB(
    ID = ID,
    infoID = infoID,
    filterName = filterName,
    minValue = minValue,
    maxValue = maxValue
) {

    object Columns {
        const val ID = "id"
        const val INFO_ID = "info_id"
        const val FILTER_NAME = "filter_name"
        const val MIN_VALUE = "min_value"
        const val MAX_VALUE = "max_value"
    }

    companion object {
        const val TABLE_NAME = "basic_of_search_filter"

        fun fromDB(item: BasicOfSearchFilterDB): BasicOfSearchFilterEntity {
            return BasicOfSearchFilterEntity(
                ID = item.ID,
                infoID = item.infoID,
                filterName = item.filterName,
                minValue = item.minValue,
                maxValue = item.maxValue
            )
        }
    }
}