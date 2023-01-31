package com.example.foodinfo.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.foodinfo.local.dao.RecipeAttrDAO
import com.example.foodinfo.local.dto.BasicRecipeAttrDB
import com.example.foodinfo.local.dto.CategoryRecipeAttrDB
import com.example.foodinfo.local.dto.LabelRecipeAttrDB
import com.example.foodinfo.local.dto.NutrientRecipeAttrDB
import com.example.foodinfo.local.room.entity.BasicRecipeAttrEntity
import com.example.foodinfo.local.room.entity.CategoryRecipeAttrEntity
import com.example.foodinfo.local.room.entity.LabelRecipeAttrEntity
import com.example.foodinfo.local.room.entity.NutrientRecipeAttrEntity


@Dao
interface RecipeAttrDAORoom : RecipeAttrDAO {

    @Query(
        "SELECT * FROM ${NutrientRecipeAttrEntity.TABLE_NAME} " +
                "WHERE ${NutrientRecipeAttrEntity.Columns.ID} = :ID"
    )
    override fun getNutrient(ID: Int): NutrientRecipeAttrEntity


    @Transaction
    @Query(
        "SELECT * FROM ${LabelRecipeAttrEntity.TABLE_NAME} " +
                "WHERE ${LabelRecipeAttrEntity.Columns.ID} = :ID"
    )
    override fun getLabel(ID: Int): LabelRecipeAttrEntity

    @Transaction
    @Query(
        "SELECT * FROM ${LabelRecipeAttrEntity.TABLE_NAME} " +
                "WHERE ${LabelRecipeAttrEntity.Columns.CATEGORY_ID} = :categoryID"
    )
    fun getCategoryLabelsEntity(categoryID: Int): List<LabelRecipeAttrEntity>

    override fun getCategoryLabels(categoryID: Int): List<LabelRecipeAttrDB> {
        return getCategoryLabelsEntity(categoryID)
    }

    @Query(
        "SELECT * FROM ${CategoryRecipeAttrEntity.TABLE_NAME} " +
                "WHERE ${CategoryRecipeAttrEntity.Columns.ID} = :ID"
    )
    override fun getCategory(ID: Int): CategoryRecipeAttrEntity


    @Query("SELECT * FROM ${CategoryRecipeAttrEntity.TABLE_NAME}")
    fun getCategoriesAllEntity(): List<CategoryRecipeAttrEntity>

    override fun getCategoriesAll(): List<CategoryRecipeAttrDB> {
        return getCategoriesAllEntity()
    }

    @Query("SELECT * FROM ${NutrientRecipeAttrEntity.TABLE_NAME}")
    fun getNutrientsAllEntity(): List<NutrientRecipeAttrEntity>

    override fun getNutrientsAll(): List<NutrientRecipeAttrDB> {
        return getNutrientsAllEntity()
    }

    @Query("SELECT * FROM ${BasicRecipeAttrEntity.TABLE_NAME}")
    fun getBasicsAllEntity(): List<BasicRecipeAttrEntity>

    override fun getBasicsAll(): List<BasicRecipeAttrDB> {
        return getBasicsAllEntity()
    }

    @Query("SELECT * FROM ${LabelRecipeAttrEntity.TABLE_NAME}")
    fun getLabelsAllEntity(): List<LabelRecipeAttrEntity>

    override fun getLabelsAll(): List<LabelRecipeAttrDB> {
        return getLabelsAllEntity()
    }


    @Insert
    fun addNutrientsEntity(attrs: List<NutrientRecipeAttrEntity>)

    override fun addNutrients(attrs: List<NutrientRecipeAttrDB>) {
        addNutrientsEntity(attrs.map { NutrientRecipeAttrEntity.fromDB(it) })
    }

    @Insert
    fun addBasicsEntity(attrs: List<BasicRecipeAttrEntity>)

    override fun addBasics(attrs: List<BasicRecipeAttrDB>) {
        addBasicsEntity(attrs.map { BasicRecipeAttrEntity.fromDB(it) })
    }

    @Insert
    fun addLabelsEntity(attrs: List<LabelRecipeAttrEntity>)

    override fun addLabels(attrs: List<LabelRecipeAttrDB>) {
        addLabelsEntity(attrs.map { LabelRecipeAttrEntity.fromDB(it) })
    }

    @Insert
    fun addCategoriesEntity(attrs: List<CategoryRecipeAttrEntity>)

    override fun addCategories(attrs: List<CategoryRecipeAttrDB>) {
        addCategoriesEntity(attrs.map { CategoryRecipeAttrEntity.fromDB(it) })
    }
}