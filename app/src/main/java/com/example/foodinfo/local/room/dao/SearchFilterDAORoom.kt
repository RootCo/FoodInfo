package com.example.foodinfo.local.room.dao

import androidx.room.*
import com.example.foodinfo.local.dao.SearchFilterDAO
import com.example.foodinfo.local.dto.*
import com.example.foodinfo.local.room.entity.*
import com.example.foodinfo.local.room.pojo.*
import kotlinx.coroutines.flow.Flow


@Dao
interface SearchFilterDAORoom : SearchFilterDAO {
    @Transaction
    @Query(
        "SELECT * FROM ${LabelOfSearchFilterEntity.TABLE_NAME} WHERE " +
                "${LabelOfSearchFilterEntity.Columns.FILTER_NAME} LIKE '%' || :filterName || '%'"
    )
    fun getLabelsPOJO(filterName: String): List<LabelOfSearchFilterExtendedPOJO>

    override fun getLabels(filterName: String): List<LabelOfSearchFilterExtendedDB> {
        return getLabelsPOJO(filterName)
    }

    @Transaction
    @Query(
        "SELECT * FROM ${NutrientOfSearchFilterEntity.TABLE_NAME} WHERE " +
                "${NutrientOfSearchFilterEntity.Columns.FILTER_NAME} LIKE '%' || :filterName || '%'"
    )
    fun getNutrientsPOJO(filterName: String): List<NutrientOfSearchFilterExtendedPOJO>

    override fun getNutrients(filterName: String): List<NutrientOfSearchFilterExtendedDB> {
        return getNutrientsPOJO(filterName)
    }

    @Transaction
    @Query(
        "SELECT * FROM ${BasicOfSearchFilterEntity.TABLE_NAME} WHERE " +
                "${BasicOfSearchFilterEntity.Columns.FILTER_NAME} LIKE '%' || :filterName || '%'"
    )
    fun getBasicsPOJO(filterName: String): List<BasicOfSearchFilterExtendedPOJO>

    override fun getBasics(filterName: String): List<BasicOfSearchFilterExtendedDB> {
        return getBasicsPOJO(filterName)
    }


    @Transaction
    @Query(
        "SELECT * FROM ${LabelOfSearchFilterEntity.TABLE_NAME} WHERE " +
                "${LabelOfSearchFilterEntity.Columns.FILTER_NAME} LIKE '%' || :filterName || '%'"
    )
    fun observeLabelsPOJO(filterName: String): Flow<List<LabelOfSearchFilterExtendedPOJO>>

    override fun observeLabels(filterName: String): Flow<List<LabelOfSearchFilterExtendedDB>> {
        return observeLabelsPOJO(filterName)
    }

    @Transaction
    @Query(
        "SELECT * FROM ${NutrientOfSearchFilterEntity.TABLE_NAME} WHERE " +
                "${NutrientOfSearchFilterEntity.Columns.FILTER_NAME} LIKE '%' || :filterName || '%'"
    )
    fun observeNutrientsPOJO(filterName: String): Flow<List<NutrientOfSearchFilterExtendedPOJO>>

    override fun observeNutrients(filterName: String): Flow<List<NutrientOfSearchFilterExtendedDB>> {
        return observeNutrientsPOJO(filterName)
    }

    @Transaction
    @Query(
        "SELECT * FROM ${SearchFilterEntity.TABLE_NAME} WHERE " +
                "${SearchFilterEntity.Columns.NAME} LIKE '%' || :filterName || '%'"
    )
    fun observeFilterExtendedPOJO(filterName: String): Flow<SearchFilterExtendedPOJO>

    override fun observeFilterExtended(filterName: String): Flow<SearchFilterExtendedDB> {
        return observeFilterExtendedPOJO(filterName)
    }


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFilterEntity(filter: SearchFilterEntity): Long

    @Insert
    fun insertBasicsEntity(baseFields: List<BasicOfSearchFilterEntity>)

    @Insert
    fun insertLabelsEntity(labels: List<LabelOfSearchFilterEntity>)

    @Insert
    fun insertNutrientsEntity(nutrients: List<NutrientOfSearchFilterEntity>)


    @Query(
        "UPDATE ${BasicOfSearchFilterEntity.TABLE_NAME} SET " +
                "${BasicOfSearchFilterEntity.Columns.MIN_VALUE} = :minValue," +
                "${BasicOfSearchFilterEntity.Columns.MAX_VALUE} = :maxValue " +
                "WHERE ${BasicOfSearchFilterEntity.Columns.ID} == :id"
    )
    override fun updateBasic(id: Int, minValue: Float, maxValue: Float)

    @Query(
        "UPDATE ${LabelOfSearchFilterEntity.TABLE_NAME} SET " +
                "${LabelOfSearchFilterEntity.Columns.IS_SELECTED} = :isSelected " +
                "WHERE ${LabelOfSearchFilterEntity.Columns.ID} == :id"
    )
    override fun updateLabel(id: Int, isSelected: Boolean)

    @Query(
        "UPDATE ${NutrientOfSearchFilterEntity.TABLE_NAME} SET " +
                "${NutrientOfSearchFilterEntity.Columns.MIN_VALUE} = :minValue," +
                "${NutrientOfSearchFilterEntity.Columns.MAX_VALUE} = :maxValue " +
                "WHERE ${NutrientOfSearchFilterEntity.Columns.ID} == :id"
    )
    override fun updateNutrient(id: Int, minValue: Float, maxValue: Float)


    @Update
    fun updateBasicsEntity(baseFields: List<BasicOfSearchFilterEntity>)

    override fun updateBasics(basics: List<BasicOfSearchFilterDB>) {
        updateBasicsEntity(basics.map { BasicOfSearchFilterEntity.fromDB(it) })
    }

    @Update
    fun updateLabelsEntity(labels: List<LabelOfSearchFilterEntity>)

    override fun updateLabels(labels: List<LabelOfSearchFilterDB>) {
        updateLabelsEntity(labels.map { LabelOfSearchFilterEntity.fromDB(it) })
    }

    @Update
    fun updateNutrientsEntity(nutrients: List<NutrientOfSearchFilterEntity>)

    override fun updateNutrients(nutrients: List<NutrientOfSearchFilterDB>) {
        updateNutrientsEntity(nutrients.map { NutrientOfSearchFilterEntity.fromDB(it) })
    }


    @Transaction
    override fun insertFilter(
        filterName: String,
        basics: List<BasicOfSearchFilterDB>,
        labels: List<LabelOfSearchFilterDB>,
        nutrients: List<NutrientOfSearchFilterDB>,
        overwrite: Boolean
    ) {
        val success = insertFilterEntity(SearchFilterEntity(name = filterName))
        if (success > 0) {
            insertBasicsEntity(basics.map { BasicOfSearchFilterEntity.fromDB(it) })
            insertLabelsEntity(labels.map { LabelOfSearchFilterEntity.fromDB(it) })
            insertNutrientsEntity(nutrients.map { NutrientOfSearchFilterEntity.fromDB(it) })
        } else if (overwrite) {
            updateBasics(basics)
            updateLabels(labels)
            updateNutrients(nutrients)
        }
    }
}