package com.example.foodinfo.model.repository.impl

import com.example.foodinfo.model.dao.FoodDAO
import com.example.foodinfo.model.entities.Food
import com.example.foodinfo.model.repository.RepositoryFood
import javax.inject.Inject

class RepositoryFoodImpl @Inject constructor(private val foodDAO: FoodDAO) :
    RepositoryFood {

    override fun getDaily(): Food {
        return foodDAO.getDaily()
    }
}