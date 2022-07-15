package com.example.foodinfo.model.local.dao

import com.example.foodinfo.model.local.Food


interface FoodDAO {
    fun getDaily(): Food
}