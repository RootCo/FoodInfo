package com.example.foodinfo.ui

import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodinfo.R
import com.example.foodinfo.databinding.FragmentExploreBinding
import com.example.foodinfo.model.local.CategoryItem
import com.example.foodinfo.ui.adapter.ExploreInnerRecipesAdapter
import com.example.foodinfo.ui.adapter.ExploreOuterRecipesAdapter
import com.example.foodinfo.utils.applicationComponent
import com.example.foodinfo.utils.restoreState
import com.example.foodinfo.view_model.ExploreViewModel
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


class ExploreFragment : BaseDataFragment<FragmentExploreBinding>(
    FragmentExploreBinding::inflate
) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: ExploreOuterRecipesAdapter

    private val viewModel: ExploreViewModel by viewModels {
        activity!!.applicationComponent.viewModelsFactory()
    }

    override fun updateViewModelData() {
    }

    override fun initUI() {
        recyclerView = binding.root.findViewById(R.id.rv_explore_outer)
        recyclerView.setHasFixedSize(true)
        recyclerView.setItemViewCacheSize(20)
        recyclerView.layoutManager = LinearLayoutManager(context).also {
            it.initialPrefetchItemCount = 3
        }

        val categoryTabs = binding.root.findViewById<TabLayout>(R.id.tl_category)

        categoryTabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                prepareTab(tab.text.toString())
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        for (category in viewModel.categories) {
            categoryTabs.addTab(categoryTabs.newTab().setText(category))
        }

        binding.root.findViewById<TextView>(R.id.tv_search).setOnClickListener {
            findNavController().navigate(
                ExploreFragmentDirections.actionFExploreToFSearchInput()
            )
        }
    }


    private val onInnerItemClickListener: (String) -> Unit = { id ->
        findNavController().navigate(
            ExploreFragmentDirections.actionFExploreToFRecipeExtended(id)
        )
    }

    private val onOuterItemClickListener: (String, String) -> Unit = { category, label ->
        findNavController().navigate(
            ExploreFragmentDirections.actionFExploreToFSearchTarget(
                category, label
            )
        )
    }

    private val onReadyToLoadData: (
        RecyclerView, ExploreInnerRecipesAdapter, CategoryItem
    ) -> Unit = { recycler, adapter, item ->
        lifecycleScope.launch {
            item.recipes.collectLatest { recipes ->
                delay(200)
                adapter.submitData(recipes)
            }
        }
        /*
            отдельная корутина т.к. submitDate не возвращает ничего и код после неё
            не отрабатывает

            всё остальное бездумно спизжено с:
            https://developer.android.com/reference/androidx/paging/AsyncPagingDataDiffer#loadStateFlow()

            чего я хотел добиться - чтобы recycler.restoreState(item.state)
            отрабатывал ПОСЛЕ adapter.submitData(recipes) т.к. если он отработает
            ДО adapter.submitData(recipes), то скролл будет на первом элементе
         */
        lifecycleScope.launch {
            adapter.loadStateFlow.map { it.refresh }
                .distinctUntilChanged()
                .collectLatest { loadState ->
                    if (loadState is LoadState.NotLoading) {
                        recycler.restoreState(item.state)
                    }
                }
        }
    }

    // вынести в viewModel
    fun prepareTab(category: String) {
        recyclerAdapter = ExploreOuterRecipesAdapter(
            binding.root.context,
            onInnerItemClickListener,
            onOuterItemClickListener,
            onReadyToLoadData,
        )

        recyclerView.adapter = recyclerAdapter

        lifecycleScope.launchWhenResumed {
            viewModel.categoryRecipes[category]?.collectLatest { recipes ->
                delay(100)
                recyclerAdapter.submitData(recipes)
            }
        }
    }
}