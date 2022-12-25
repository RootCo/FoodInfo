package com.example.foodinfo.local.dao

import androidx.room.*
import com.example.foodinfo.local.entity.*
import com.example.foodinfo.local.pojo.BaseFieldFilterPOJO
import com.example.foodinfo.local.pojo.NutrientFilterPOJO
import com.example.foodinfo.local.pojo.SearchFilterEditPOJO
import kotlinx.coroutines.flow.Flow


@Dao
interface SearchFilterDAO {
    @Query(
        "SELECT * FROM ${LabelFilterEntity.TABLE_NAME} WHERE " +
                "${LabelFilterEntity.Columns.FILTER_NAME} LIKE '%' || :filterName || '%' AND " +
                "${LabelFilterEntity.Columns.CATEGORY}  LIKE '%' || :categoryName || '%'"
    )
    fun getLabelsCategory(filterName: String, categoryName: String): List<LabelFilterEntity>

    @Query(
        "SELECT * FROM ${LabelFilterEntity.TABLE_NAME} WHERE " +
                "${LabelFilterEntity.Columns.FILTER_NAME} LIKE '%' || :filterName || '%'"
    )
    fun getLabelsAll(filterName: String): List<LabelFilterEntity>

    @Transaction
    @Query(
        "SELECT * FROM ${NutrientFilterEntity.TABLE_NAME} WHERE " +
                "${NutrientFilterEntity.Columns.FILTER_NAME} LIKE '%' || :filterName || '%'"
    )
    fun getNutrients(filterName: String): List<NutrientFilterPOJO>

    @Transaction
    @Query(
        "SELECT * FROM ${BaseFieldFilterEntity.TABLE_NAME} WHERE " +
                "${BaseFieldFilterEntity.Columns.FILTER_NAME} LIKE '%' || :filterName || '%'"
    )
    fun getBaseFields(filterName: String): List<BaseFieldFilterPOJO>


    @Query(
        "SELECT * FROM ${LabelFilterEntity.TABLE_NAME} WHERE " +
                "${LabelFilterEntity.Columns.FILTER_NAME} LIKE '%' || :filterName || '%' AND " +
                "${LabelFilterEntity.Columns.CATEGORY}  LIKE '%' || :categoryName || '%'"
    )
    fun observeLabelsCategory(filterName: String, categoryName: String): Flow<List<LabelFilterEntity>>

    @Transaction
    @Query(
        "SELECT * FROM ${NutrientFilterEntity.TABLE_NAME} WHERE " +
                "${NutrientFilterEntity.Columns.FILTER_NAME} LIKE '%' || :filterName || '%'"
    )
    fun observeNutrients(filterName: String): Flow<List<NutrientFilterPOJO>>

    @Transaction
    @Query(
        "SELECT * FROM ${SearchFilterEntity.TABLE_NAME} WHERE " +
                "${SearchFilterEntity.Columns.NAME} LIKE '%' || :filterName || '%'"
    )
    fun observeFilter(filterName: String): Flow<SearchFilterEditPOJO>


    @Query(
        "UPDATE ${BaseFieldFilterEntity.TABLE_NAME} SET " +
                "${BaseFieldFilterEntity.Columns.MIN_VALUE} = :minValue," +
                "${BaseFieldFilterEntity.Columns.MAX_VALUE} = :maxValue " +
                "WHERE ${BaseFieldFilterEntity.Columns.ID} == :id"
    )
    fun updateBaseField(id: Long, minValue: Float, maxValue: Float)

    @Query(
        "UPDATE ${NutrientFilterEntity.TABLE_NAME} SET " +
                "${NutrientFilterEntity.Columns.MIN_VALUE} = :minValue," +
                "${NutrientFilterEntity.Columns.MAX_VALUE} = :maxValue " +
                "WHERE ${NutrientFilterEntity.Columns.ID} == :id"
    )
    fun updateNutrient(id: Long, minValue: Float, maxValue: Float)

    @Query(
        "UPDATE ${LabelFilterEntity.TABLE_NAME} SET " +
                "${LabelFilterEntity.Columns.IS_SELECTED} = :isSelected " +
                "WHERE ${LabelFilterEntity.Columns.ID} == :id"
    )
    fun updateLabel(id: Long, isSelected: Boolean)


    @Update
    fun updateLabels(labels: List<LabelFilterEntity>)

    @Update
    fun updateNutrients(nutrients: List<NutrientFilterEntity>)

    @Update
    fun updateBaseFields(fields: List<BaseFieldFilterEntity>)


    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertFilter(filter: SearchFilterEntity)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertLabels(labels: List<LabelFilterEntity>)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertNutrients(nutrients: List<NutrientFilterEntity>)

    @Insert(onConflict = OnConflictStrategy.ABORT)
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