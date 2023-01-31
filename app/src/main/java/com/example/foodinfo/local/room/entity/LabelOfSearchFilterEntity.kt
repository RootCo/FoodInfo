package com.example.foodinfo.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.foodinfo.local.dto.LabelOfSearchFilterDB


@Entity(
    tableName = LabelOfSearchFilterEntity.TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = SearchFilterEntity::class,
        parentColumns = arrayOf(SearchFilterEntity.Columns.NAME),
        childColumns = arrayOf(LabelOfSearchFilterEntity.Columns.FILTER_NAME),
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
    )]
)
data class LabelOfSearchFilterEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Columns.ID)
    override val ID: Int = 0,

    @ColumnInfo(name = Columns.FILTER_NAME)
    override val filterName: String,

    @ColumnInfo(name = Columns.INFO_ID)
    override val infoID: Int,

    @ColumnInfo(name = Columns.IS_SELECTED)
    override val isSelected: Boolean

) : LabelOfSearchFilterDB(
    ID = ID,
    filterName = filterName,
    infoID = infoID,
    isSelected = isSelected
) {

    object Columns {
        const val ID = "id"
        const val FILTER_NAME = "filter_name"
        const val INFO_ID = "info_id"
        const val IS_SELECTED = "is_selected"
    }

    companion object {
        const val TABLE_NAME = "label_of_search_filter"

        fun fromDB(item: LabelOfSearchFilterDB): LabelOfSearchFilterEntity {
            return LabelOfSearchFilterEntity(
                ID = item.ID,
                filterName = item.filterName,
                infoID = item.infoID,
                isSelected = item.isSelected
            )
        }
    }
}