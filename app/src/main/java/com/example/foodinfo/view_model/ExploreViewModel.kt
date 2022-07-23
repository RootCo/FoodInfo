package com.example.foodinfo.view_model

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.recyclerview.widget.RecyclerView
import com.example.foodinfo.model.local.CategoryItem
import com.example.foodinfo.model.local.RecipeExplore
import com.example.foodinfo.model.local.dao.filter_field.CategoryField
import com.example.foodinfo.model.local.entities.SearchFilter
import com.example.foodinfo.model.repository.RepositoryRecipes
import com.example.foodinfo.model.repository.impl.RepositoryRecipesImpl
import com.example.foodinfo.ui.adapter.ExploreInnerRecipesAdapter
import com.example.foodinfo.ui.adapter.ExploreOuterRecipesAdapter
import com.example.foodinfo.utils.restoreState
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject


class ExploreViewModel @Inject constructor(
    private val context: Application,
    private val repositoryRecipes: RepositoryRecipes
) : ViewModel() {

    private val adapters: HashMap<String, ExploreOuterRecipesAdapter> = hashMapOf()
    private val categoryItems = hashMapOf<String, StateFlow<PagingData<CategoryItem>>>()

    private var submitCategoryJobs: HashMap<String, Job> = hashMapOf()
    private var submitLabelJobs: HashMap<String, Job> = hashMapOf()
    private var restoreLabelJobs: HashMap<String, Job> = hashMapOf()

    private val readyToSubscribe: (
        ExploreInnerRecipesAdapter,
        CategoryItem
    ) -> Unit = { adapter, item ->
        submitLabelJobs[item.label]?.cancel()
        submitLabelJobs[item.label] = viewModelScope.launch {
            item.recipes.collectLatest { recipes ->
                delay(250)
                adapter.submitData(recipes)
            }
        }
    }

    private val readyToRestoreState: (
        ExploreInnerRecipesAdapter,
        CategoryItem,
        RecyclerView
    ) -> Unit = { adapter, item, recycler ->
        restoreLabelJobs[item.label]?.cancel()
        restoreLabelJobs[item.label] = viewModelScope.launch {
            adapter.loadStateFlow.map { it.refresh }
                .distinctUntilChanged()
                .collectLatest { loadState ->
                    if (loadState is LoadState.NotLoading) {
                        recycler.restoreState(item.state)
                        cancel()
                    }
                }
        }
    }

    private val getRecipes: (SearchFilter) -> StateFlow<PagingData<RecipeExplore>> =
        { filter ->
            repositoryRecipes.getByFilterExplore(filter)
                .flowOn(Dispatchers.IO)
                .cachedIn(viewModelScope)
                .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
        }


    val adapter: ExploreOuterRecipesAdapter
        get() {
            return adapters[tabLabel]!!.also { adapter ->
                submitCategoryJobs[tabLabel]?.cancel()
                submitCategoryJobs[tabLabel] = viewModelScope.launch {
                    categoryItems[tabLabel]?.collectLatest { categoryItem ->
                        delay(100)
                        adapter.submitData(categoryItem)
                    }
                }
            }
        }

    val categories = CategoryField.Fields.values().map { it.label }
    var tabLabel = categories[0]
        private set
    var tabIndex = 0
        private set


    init {
        for (category in categories) {
            categoryItems[category] = Pager(
                config = RepositoryRecipesImpl.DB_EXPLORE_OUTER_PAGER,
                pagingSourceFactory = {
                    ExploreCategoriesDataSource(getRecipes, category)
                }).flow
                .flowOn(Dispatchers.IO)
                .cachedIn(viewModelScope)
                .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
        }
    }

    fun updateTab(tab: TabLayout.Tab) {
        submitCategoryJobs[tabLabel]?.cancel()
        tabLabel = tab.text.toString()
        tabIndex = tab.id
    }

    fun initAdapters(
        onInnerItemClickListener: (String) -> Unit,
        onOuterItemClickListener: (String, String) -> Unit
    ) {
        for (category in categories) {
            adapters[category] = ExploreOuterRecipesAdapter(
                context,
                onInnerItemClickListener,
                onOuterItemClickListener,
                readyToRestoreState,
                readyToSubscribe
            )
        }
    }
}