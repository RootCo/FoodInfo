package com.example.foodinfo.repository.impl

import com.example.foodinfo.local.dao.NutrientsDAO
import com.example.foodinfo.local.model.NutrientModel
import com.example.foodinfo.repository.RepositoryNutrients
import com.example.foodinfo.repository.mapper.toModel
import javax.inject.Inject


class RepositoryNutrientsImpl @Inject constructor(
    private val nutrientsDAO: NutrientsDAO
) : RepositoryNutrients {
    override fun getByLabel(label: String): NutrientModel {
        return nutrientsDAO.getByLabel(label).toModel()
    }

    override fun getAll(): List<NutrientModel> {
        return nutrientsDAO.getAll().map { it.toModel() }
    }
}