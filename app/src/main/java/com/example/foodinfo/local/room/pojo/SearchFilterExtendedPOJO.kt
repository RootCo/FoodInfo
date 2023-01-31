package com.example.foodinfo.local.room.pojo

import androidx.room.ColumnInfo
import androidx.room.Relation
import com.example.foodinfo.local.dto.SearchFilterDB
import com.example.foodinfo.local.dto.SearchFilterExtendedDB
import com.example.foodinfo.local.room.entity.BasicOfSearchFilterEntity
import com.example.foodinfo.local.room.entity.LabelOfSearchFilterEntity
import com.example.foodinfo.local.room.entity.NutrientOfSearchFilterEntity
import com.example.foodinfo.local.room.entity.SearchFilterEntity


data class SearchFilterExtendedPOJO(
    @ColumnInfo(name = SearchFilterEntity.Columns.NAME)
    override val name: String = SearchFilterDB.DEFAULT_NAME,

    @Relation(
        parentColumn = SearchFilterEntity.Columns.NAME,
        entityColumn = BasicOfSearchFilterEntity.Columns.FILTER_NAME,
        entity = BasicOfSearchFilterEntity::class
    )
    override val basic: List<BasicOfSearchFilterExtendedPOJO>,

    @Relation(
        parentColumn = SearchFilterEntity.Columns.NAME,
        entityColumn = LabelOfSearchFilterEntity.Columns.FILTER_NAME,
        entity = LabelOfSearchFilterEntity::class
    )
    override val labels: List<LabelOfSearchFilterExtendedPOJO>,

    @Relation(
        parentColumn = SearchFilterEntity.Columns.NAME,
        entityColumn = NutrientOfSearchFilterEntity.Columns.FILTER_NAME,
        entity = NutrientOfSearchFilterEntity::class
    )
    override val nutrients: List<NutrientOfSearchFilterExtendedPOJO>

) : SearchFilterExtendedDB(
    name = name,
    basic = basic,
    labels = labels,
    nutrients = nutrients
)