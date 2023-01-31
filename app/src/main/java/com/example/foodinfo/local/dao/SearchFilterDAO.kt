package com.example.foodinfo.local.dao

import com.example.foodinfo.local.dto.*
import kotlinx.coroutines.flow.Flow


interface SearchFilterDAO {

    fun getBasics(filterName: String): List<BasicOfSearchFilterExtendedDB>

    fun getLabels(filterName: String): List<LabelOfSearchFilterExtendedDB>

    fun getNutrients(filterName: String): List<NutrientOfSearchFilterExtendedDB>


    fun observeLabels(filterName: String): Flow<List<LabelOfSearchFilterExtendedDB>>

    fun observeNutrients(filterName: String): Flow<List<NutrientOfSearchFilterExtendedDB>>

    fun observeFilterExtended(filterName: String): Flow<SearchFilterExtendedDB>


    fun updateBasic(id: Int, minValue: Float, maxValue: Float)

    fun updateLabel(id: Int, isSelected: Boolean)

    fun updateNutrient(id: Int, minValue: Float, maxValue: Float)


    fun updateBasics(basics: List<BasicOfSearchFilterDB>)

    fun updateLabels(labels: List<LabelOfSearchFilterDB>)

    fun updateNutrients(nutrients: List<NutrientOfSearchFilterDB>)


    fun insertFilter(
        filterName: String,
        basics: List<BasicOfSearchFilterDB>,
        labels: List<LabelOfSearchFilterDB>,
        nutrients: List<NutrientOfSearchFilterDB>,
        overwrite: Boolean = true
    )
}
