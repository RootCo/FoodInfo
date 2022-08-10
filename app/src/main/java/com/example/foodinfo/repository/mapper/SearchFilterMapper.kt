package com.example.foodinfo.repository.mapper

import com.example.foodinfo.local.entity.SearchFilterEntity
import com.example.foodinfo.repository.model.SearchFilterModel
import com.example.foodinfo.repository.model.filter_field.CategoryField
import com.example.foodinfo.repository.model.filter_field.NutrientField
import com.example.foodinfo.repository.model.filter_field.RangeField
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


fun SearchFilterEntity.toModel(): SearchFilterModel {
    return SearchFilterModel(
        id = this.id,
        name = this.name,
        inputText = this.inputText,
        rangeFields = Gson().fromJson(
            this.rangeFields,
            object : TypeToken<List<RangeField>>() {}.type
        ),
        nutrientFields = Gson().fromJson(
            this.nutrientFields,
            object : TypeToken<List<NutrientField>>() {}.type
        ),
        categoryFields = Gson().fromJson(
            this.categoryFields,
            object : TypeToken<List<CategoryField>>() {}.type
        )
    )
}

fun SearchFilterModel.toEntity(): SearchFilterEntity {
    return SearchFilterEntity(
        name = this.name,
        inputText = this.inputText,
        rangeFields = Gson().toJson(this.rangeFields),
        nutrientFields = Gson().toJson(this.nutrientFields),
        categoryFields = Gson().toJson(this.categoryFields)
    )
}