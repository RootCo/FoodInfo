package com.example.foodinfo.model.dao

import com.example.foodinfo.model.entities.Food

interface FoodDAO {
    fun getDaily(): Food
}