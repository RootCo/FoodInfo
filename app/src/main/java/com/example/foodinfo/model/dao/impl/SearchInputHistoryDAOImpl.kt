package com.example.foodinfo.model.dao.impl

import com.example.foodinfo.model.dao.SearchInputHistoryDAO
import com.example.foodinfo.utils.AssetProvider
import com.example.foodinfo.utils.AssetsKeyWords
import com.example.foodinfo.utils.JSONLoader
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class SearchInputHistoryDAOImpl @Inject constructor(assetProvider: AssetProvider) :
    SearchInputHistoryDAO {
    private val searchHistory: List<String>

    init {
        val jsonLoader = JSONLoader()
        val gson = GsonBuilder().create()

        val historyString =
            jsonLoader.load(assetProvider.getAsset(AssetsKeyWords.SEARCH_INPUT_HISTORY_ASSET))
                .get(AssetsKeyWords.CONTENT).toString()
        val historyType = object : TypeToken<List<String>>() {}.type

        searchHistory = gson.fromJson(historyString, historyType)
    }

    override fun getHistory(): List<String> {
        return searchHistory.mapTo(arrayListOf()) { it }
    }
}