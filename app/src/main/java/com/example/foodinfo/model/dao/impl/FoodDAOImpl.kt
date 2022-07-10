package com.example.foodinfo.model.dao.impl

import com.example.foodinfo.model.dao.FoodDAO
import com.example.foodinfo.model.entities.Food
import com.example.foodinfo.utils.AssetProvider
import com.example.foodinfo.utils.AssetsKeyWords
import com.example.foodinfo.utils.JSONLoader
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import javax.inject.Inject
import kotlin.random.Random

class FoodDAOImpl @Inject constructor(assetProvider: AssetProvider) : FoodDAO {

    private val foodHome: List<Food>

    init {
        val jsonLoader = JSONLoader()
        val gson = GsonBuilder().create()

        val foodString =
            jsonLoader.load(assetProvider.getAsset(AssetsKeyWords.FOOD_ASSET))
                .get(AssetsKeyWords.SHORT).toString()
        val typeFood = object : TypeToken<List<Food>>() {}.type

        foodHome = gson.fromJson(foodString, typeFood)
    }

    override fun getDaily(): Food {
        return foodHome[Random.nextInt(foodHome.size)].copy()
    }
}