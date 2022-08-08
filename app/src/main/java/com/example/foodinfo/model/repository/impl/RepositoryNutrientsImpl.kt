package com.example.foodinfo.model.repository.impl

import com.example.foodinfo.model.local.Nutrient
import com.example.foodinfo.model.local.dao.NutrientsDAO
import com.example.foodinfo.model.repository.RepositoryNutrients
import com.example.foodinfo.utils.ResourcesProvider
import javax.inject.Inject


class RepositoryNutrientsImpl @Inject constructor(
    private val resourcesProvider: ResourcesProvider,
    private val nutrientsDAO: NutrientsDAO
) : RepositoryNutrients {
    override fun getByLabel(label: String): Nutrient {
        return Nutrient.fromEntity(nutrientsDAO.getByLabel(label), resourcesProvider)
    }

    override fun getAll(): List<Nutrient> {
        return nutrientsDAO.getAll().map { entity ->
            Nutrient.fromEntity(entity, resourcesProvider)
        }
    }
}