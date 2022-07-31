package com.example.foodinfo.view_model

import android.content.Context
import android.os.Parcelable
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
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject


class ExploreViewModel @Inject constructor(
    private val repositoryRecipes: RepositoryRecipes
) : ViewModel() {

    private val tabsItems = hashMapOf<Int, StateFlow<PagingData<CategoryItem>>>()
    private val tabsStates = hashMapOf<Int, Parcelable>()
    private val tabsAdapters = hashMapOf<Int, ExploreOuterRecipesAdapter>()

    // Collections of Jobs for canceling previous started job to prevent memory leaks
    private var submitTabJobs = hashMapOf<Int, Job>()
    private var restoreTabJobs = hashMapOf<Int, Job>()
    private var submitLabelJobs = hashMapOf<String, Job>()
    private var restoreLabelJobs = hashMapOf<String, Job>()
    private val adapter: ExploreOuterRecipesAdapter
        get() {
            return tabsAdapters[tabIndex]!!.also { adapter ->
                submitTabJobs[tabIndex]?.cancel()
                submitTabJobs[tabIndex] = viewModelScope.launch {
                    tabsItems[tabIndex]?.collectLatest { categoryItem ->
                        delay(100) // for smooth animation
                        adapter.submitData(categoryItem)
                    }
                }
            }
        }

    private val readyToSubscribeLabel: (
        ExploreInnerRecipesAdapter,
        CategoryItem
    ) -> Unit = { adapter, item ->
        submitLabelJobs[item.label]?.cancel()
        submitLabelJobs[item.label] = viewModelScope.launch {
            item.recipes.collectLatest { recipes ->
                delay(250) // for smooth animation
                adapter.submitData(recipes)
            }
        }
    }

    /*
        Calling recycler.restoreState() right after setting up adapter for recycler
        causes situations where state restoration executes faster than adapter creates
        it's items so state will restored with scroll position at the very start.
        It's possible to avoid that by setting up delay before state restoration but
        it's not good because sometimes that delay might be less than needed, too big
        delay is also not a good option so below code calls restoreState only when
        adapter is ready for that
     */
    private val readyToRestoreStateLabel: (
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
                        cancel() // doesn't work properly without that
                    }
                }
        }
    }

    private val readyToRestoreStateTab: (
        RecyclerView
    ) -> Unit = { recycler ->
        restoreTabJobs[tabIndex]?.cancel()
        restoreTabJobs[tabIndex] = viewModelScope.launch {
            adapter.loadStateFlow.map { it.refresh }
                .distinctUntilChanged()
                .collectLatest { loadState ->
                    if (loadState is LoadState.NotLoading) {
                        recycler.restoreState(tabsStates[tabIndex])
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

    val categories = CategoryField.Fields.values().map { it.label }
    var tabIndex = 0
        private set


    init {
        categories.forEachIndexed { index, category ->
            tabsItems[index] = Pager(
                config = RepositoryRecipesImpl.DB_EXPLORE_OUTER_PAGER,
                pagingSourceFactory = {
                    ExploreCategoriesDataSource(getRecipes, category)
                }).flow
                .flowOn(Dispatchers.IO)
                .cachedIn(viewModelScope)
                .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
        }
    }

    fun updateTab(newIndex: Int, recycler: RecyclerView) {
        submitTabJobs[tabIndex]?.cancel()
        tabsStates[tabIndex] = recycler.layoutManager?.onSaveInstanceState()!!

        tabIndex = newIndex

        recycler.adapter = adapter
        readyToRestoreStateTab.invoke(recycler)
    }

    fun initAdapters(
        context: Context,
        onInnerItemClickListener: (String) -> Unit,
        onOuterItemClickListener: (String, String) -> Unit
    ) {
        categories.forEachIndexed { index, _ ->
            tabsAdapters[index] = ExploreOuterRecipesAdapter(
                context,
                onInnerItemClickListener,
                onOuterItemClickListener,
                readyToRestoreStateLabel,
                readyToSubscribeLabel
            )
        }
    }
}