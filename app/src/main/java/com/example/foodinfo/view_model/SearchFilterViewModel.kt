package com.example.foodinfo.view_model

import androidx.lifecycle.ViewModel
import com.example.foodinfo.model.repository.RepositorySearchFilter
import javax.inject.Inject

class SearchFilterViewModel @Inject constructor(
    private val repositorySearchFilter: RepositorySearchFilter
) : ViewModel() {

    // имитация обновления фильтра (якобы сюда передался введенные пользователем данные)
    fun updateFilter() {
        return
    }
}