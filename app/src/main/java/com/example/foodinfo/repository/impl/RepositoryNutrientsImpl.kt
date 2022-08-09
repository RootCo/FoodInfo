package com.example.foodinfo.repository.impl

import com.example.foodinfo.local.model.Nutrient
import com.example.foodinfo.local.dao.NutrientsDAO
import com.example.foodinfo.repository.RepositoryNutrients
import javax.inject.Inject


class RepositoryNutrientsImpl @Inject constructor(
    private val nutrientsDAO: NutrientsDAO
) : RepositoryNutrients {
    override fun getByLabel(label: String): Nutrient {
        return Nutrient.fromEntity(nutrientsDAO.getByLabel(label))
    }

    override fun getAll(): List<Nutrient> {
        return nutrientsDAO.getAll().map { entity ->
            Nutrient.fromEntity(entity)
        }
    }
}