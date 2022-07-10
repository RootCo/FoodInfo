package com.example.foodinfo.model.repository

import com.example.foodinfo.model.entities.Food

interface RepositoryFood {
    fun getDaily(): Food
}