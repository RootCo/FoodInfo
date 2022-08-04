package com.example.foodinfo.view_model

import android.content.Context
import android.os.Parcelable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.example.foodinfo.model.local.CategoryItem
import com.example.foodinfo.model.local.dao.filter_field.CategoryField
import com.example.foodinfo.ui.adapter.ExploreCategoryAdapter
import com.example.foodinfo.utils.ResourcesProvider
import com.example.foodinfo.utils.restoreState
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject


class ExploreViewModel @Inject constructor(
    private val resourcesProvider: ResourcesProvider
) : ViewModel() {

    // Collection of Jobs for canceling previous started job to prevent memory leaks
    private var submitTabJobs = hashMapOf<Int, Job>()

    private val tabsItems = hashMapOf<Int, List<CategoryItem>>()
    private val tabsStates = hashMapOf<Int, Parcelable>()
    private val tabsAdapters = hashMapOf<Int, ExploreCategoryAdapter>()

    private val adapter: ExploreCategoryAdapter
        get() {
            return tabsAdapters[tabIndex]!!.also { adapter ->
                submitTabJobs[tabIndex]?.cancel()
                submitTabJobs[tabIndex] = viewModelScope.launch {
                    adapter.submitList(getItems())
                }
            }
        }

    val categories = CategoryField.Fields.values().mapIndexed { index, field ->
        index to field
    }.toMap()

    var tabIndex = 0
        private set


    private fun getItems(): List<CategoryItem> {
        if (tabsItems[tabIndex] == null) {
            val icons = categories[tabIndex]!!.icons
            val labels = categories[tabIndex]!!.labels
            val category = categories[tabIndex]!!.label
            tabsItems[tabIndex] = labels.zip(icons).map { pair ->
                CategoryItem(
                    category,
                    pair.first,
                    resourcesProvider.getDrawableByName(pair.second)!!
                )
            }
        }
        return tabsItems[tabIndex]!!
    }

    fun updateTab(newIndex: Int, recycler: RecyclerView) {
        submitTabJobs[tabIndex]?.cancel()
        tabsStates[tabIndex] = recycler.layoutManager?.onSaveInstanceState()!!

        tabIndex = newIndex

        recycler.adapter = adapter
        recycler.restoreState(tabsStates[tabIndex])
    }

    fun initAdapters(
        context: Context,
        onItemClickListener: (String, String) -> Unit
    ) {
        for (index in categories.keys) {
            tabsAdapters[index] = ExploreCategoryAdapter(
                context,
                onItemClickListener
            )
        }
    }
}