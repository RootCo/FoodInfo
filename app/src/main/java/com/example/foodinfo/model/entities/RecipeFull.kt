package com.example.foodinfo.model.entities

import android.graphics.drawable.Drawable
import java.net.URL

data class RecipeFull(
    val id: String,
    val name: String,
    val preview: Drawable?,
    val calories: Int,
    val protein: Int,
    val fat: Int,
    val carb: Int,
    val source: URL, // ссылка на рецепт
    val dietLabels: List<Int>, // сделать енум с диетами
    val healthLabels: List<Int>, // сделать енум с типами здорового питания
    val cautions: List<Int>, // сделать енум со странами
    val ingredientLines: List<String>, // список ингредиентов для превью
    val ingredients: List<Ingredient>, // список ингредиентов
    val totalWeight: Int,
    val totalTime: Int,
    val cuisineType: List<Int>, // сделать енум с ореоломи
    val mealType: List<Int>, // сделать енум временами приема пищи (завтрак, обед...)
    val dishType: List<Int>, // сделать енум типов еды (выпечка, десерты, первое...)
    val totalNutrients: List<Nutrient>, // TODO
    val totalDaily: List<Nutrient> // TODO
)