package com.example.foodinfo.local.dao

import androidx.room.*
import com.example.foodinfo.local.entity.*
import com.example.foodinfo.local.pojo.BaseFieldFilterPOJO
import com.example.foodinfo.local.pojo.NutrientFilterPOJO


@Dao
interface SearchFilterDAO {
    @Query(
        "SELECT * FROM ${SearchFilterEntity.TABLE_NAME} " +
                "WHERE ${SearchFilterEntity.Columns.NAME} " +
                "LIKE '%' || :filterName || '%'"
    )
    fun getFilter(filterName: String): SearchFilterEntity

    @Query(
        "SELECT * FROM ${LabelFilterEntity.TABLE_NAME} " +
                "WHERE ${LabelFilterEntity.Columns.FILTER_NAME} " +
                "LIKE '%' || :filterName || '%' " +
                "AND ${LabelFilterEntity.Columns.CATEGORY} " +
                "LIKE '%' || :categoryName || '%'"
    )
    fun getCategory(filterName: String, categoryName: String): List<LabelFilterEntity>

    @Query(
        "SELECT * FROM ${LabelFilterEntity.TABLE_NAME} " +
                "WHERE ${LabelFilterEntity.Columns.FILTER_NAME} " +
                "LIKE '%' || :filterName || '%'"
    )
    fun getCategories(filterName: String): List<LabelFilterEntity>

    @Query(
        "SELECT * FROM ${NutrientFilterEntity.TABLE_NAME} " +
                "WHERE ${NutrientFilterEntity.Columns.FILTER_NAME} " +
                "LIKE '%' || :filterName || '%'"
    )
    fun getNutrients(filterName: String): List<NutrientFilterPOJO>

    @Query(
        "SELECT * FROM ${BaseFieldFilterEntity.TABLE_NAME} " +
                "WHERE ${BaseFieldFilterEntity.Columns.FILTER_NAME} " +
                "LIKE '%' || :filterName || '%'"
    )
    fun getBaseFields(filterName: String): List<BaseFieldFilterPOJO>


    @Update
    fun updateCategory(labels: List<LabelFilterEntity>)

    @Update
    fun updateNutrients(nutrients: List<NutrientFilterEntity>)

    @Update
    fun updateBaseFields(fields: List<BaseFieldFilterEntity>)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFilter(filter: SearchFilterEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLabels(labels: List<LabelFilterEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNutrients(nutrients: List<NutrientFilterEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBaseFields(baseFields: List<BaseFieldFilterEntity>)

    @Transaction
    fun initializeFilter(
        filterName: String,
        labels: List<LabelFilterEntity>,
        nutrients: List<NutrientFilterEntity>,
        baseFields: List<BaseFieldFilterEntity>
    ) {
        insertFilter(SearchFilterEntity(name = filterName))
        insertLabels(labels)
        insertNutrients(nutrients)
        insertBaseFields(baseFields)
    }
}