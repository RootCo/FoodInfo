package com.example.foodinfo.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodinfo.local.model.RecipeExtended
import com.example.foodinfo.repository.RepositoryRecipes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject

class RecipeExtendedViewModel @Inject constructor(repositoryRecipes: RepositoryRecipes) :
    ViewModel() {

    var recipeId: String = ""

    val recipe: SharedFlow<RecipeExtended> by lazy {
        repositoryRecipes.getByIdExtended(recipeId).flowOn(Dispatchers.IO)
            .shareIn(viewModelScope, SharingStarted.WhileSubscribed(), 1)
    }
}