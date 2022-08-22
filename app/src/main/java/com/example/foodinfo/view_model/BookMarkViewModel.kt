package com.example.foodinfo.view_model

import androidx.lifecycle.ViewModel
import com.example.foodinfo.repository.RepositoryRecipes
import javax.inject.Inject

class BookMarkViewModel @Inject constructor(
    private val repositoryRecipes: RepositoryRecipes
) : ViewModel() {

}