package com.example.foodinfo.local.pojo

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.foodinfo.local.entity.BaseFieldFilterEntity
import com.example.foodinfo.local.entity.LabelFilterEntity
import com.example.foodinfo.local.entity.NutrientFilterEntity
import com.example.foodinfo.local.entity.SearchFilterEntity


data class SearchFilterEditPOJO(
    @PrimaryKey
    @ColumnInfo(name = SearchFilterEntity.Columns.ID)
    val id: Long,

    @ColumnInfo(name = SearchFilterEntity.Columns.NAME)
    val name: String,

    @Relation(
        parentColumn = SearchFilterEntity.Columns.NAME,
        entityColumn = BaseFieldFilterEntity.Columns.FILTER_NAME,
        entity = BaseFieldFilterEntity::class
    )
    val baseFields: List<BaseFieldFilterEntity>,

    @Relation(
        parentColumn = SearchFilterEntity.Columns.NAME,
        entityColumn = LabelFilterEntity.Columns.FILTER_NAME
    )
    val categories: List<LabelFilterEntity>,

    @Relation(
        parentColumn = SearchFilterEntity.Columns.NAME,
        entityColumn = NutrientFilterEntity.Columns.FILTER_NAME,
        entity = NutrientFilterEntity::class
    )
    val nutrients: List<NutrientFilterEntity>
)