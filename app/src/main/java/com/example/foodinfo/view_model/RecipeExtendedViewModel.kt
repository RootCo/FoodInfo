package com.example.foodinfo.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodinfo.repository.RepositoryLabels
import com.example.foodinfo.repository.RepositoryRecipes
import com.example.foodinfo.repository.model.*
import com.example.foodinfo.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject


class RecipeExtendedViewModel @Inject constructor(
    private val repositoryRecipes: RepositoryRecipes,
    private val repositoryLabels: RepositoryLabels
) :
    ViewModel() {

    var recipeId: String = ""

    val recipe: SharedFlow<Resource<RecipeModel>> by lazy {
        repositoryRecipes.getById(recipeId).onStart { emit(Resource.Init(RecipeModel("","","",0,"",0,0,0,"",false))) }.flowOn(Dispatchers.IO)
            .shareIn(viewModelScope, SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000)) //то про что я тебе говорил у тебя не стоит а ещё init value надо добавить на все
    }

    val ingredients: SharedFlow<Resource<List<RecipeIngredientModel>>> by lazy {
        repositoryRecipes.getByIdIngredients(recipeId).flowOn(Dispatchers.IO)
            .shareIn(viewModelScope, SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000))
    }

    val nutrients: SharedFlow<Resource<List<RecipeNutrientModel>>> by lazy {
        repositoryRecipes.getByIdNutrients(recipeId).flowOn(Dispatchers.IO)
            .shareIn(viewModelScope, SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000))
    }

    val labels: SharedFlow<Resource<RecipeLabelsModel>> by lazy {
        repositoryRecipes.getByIdLabels(recipeId).flowOn(Dispatchers.IO)
            .shareIn(viewModelScope, SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000))
    }


    fun getLabel(category: String, label: String): LabelModel {
        return repositoryLabels.getLabel(category, label)
    }

    fun updateFavoriteMark() {
        repositoryRecipes.updateFavoriteMark(recipeId)
    }
}