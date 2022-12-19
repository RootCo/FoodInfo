package com.example.foodinfo.local.pojo

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.foodinfo.local.entity.BaseFieldEntity
import com.example.foodinfo.local.entity.BaseFieldFilterEntity


data class BaseFieldFilterPOJO(
    @PrimaryKey
    @ColumnInfo(name = BaseFieldFilterEntity.Columns.ID)
    val id: Long,

    @ColumnInfo(name = BaseFieldFilterEntity.Columns.NAME)
    val name: String,

    @ColumnInfo(name = BaseFieldFilterEntity.Columns.FILTER_NAME)
    val filterName: String,

    @ColumnInfo(name = BaseFieldFilterEntity.Columns.MIN_VALUE)
    val minValue: Float,

    @ColumnInfo(name = BaseFieldFilterEntity.Columns.MAX_VALUE)
    val maxValue: Float,

    @Relation(
        parentColumn = BaseFieldFilterEntity.Columns.NAME,
        entityColumn = BaseFieldEntity.Columns.NAME
    )
    val fieldInfo: BaseFieldEntity
)