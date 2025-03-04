package com.example.foodinfo.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.foodinfo.local.dto.LabelOfSearchFilterDB
import com.example.foodinfo.local.dto.SearchFilterDB


@Entity(
    tableName = LabelOfSearchFilterDB.TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = SearchFilterEntity::class,
        parentColumns = arrayOf(SearchFilterDB.Columns.NAME),
        childColumns = arrayOf(LabelOfSearchFilterDB.Columns.FILTER_NAME),
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

    companion object {
        fun toEntity(item: LabelOfSearchFilterDB): LabelOfSearchFilterEntity {
            return LabelOfSearchFilterEntity(
                ID = item.ID,
                filterName = item.filterName,
                infoID = item.infoID,
                isSelected = item.isSelected
            )
        }
    }
}