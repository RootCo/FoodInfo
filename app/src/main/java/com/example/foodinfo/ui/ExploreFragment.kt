package com.example.foodinfo.ui

import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodinfo.R
import com.example.foodinfo.databinding.FragmentExploreBinding
import com.example.foodinfo.model.local.RecipeExplore
import com.example.foodinfo.ui.adapter.ExploreInnerRecipesAdapter
import com.example.foodinfo.ui.adapter.ExploreOuterRecipesAdapter
import com.example.foodinfo.utils.applicationComponent
import com.example.foodinfo.view_model.ExploreViewModel
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest


class ExploreFragment : BaseDataFragment<FragmentExploreBinding>(
    FragmentExploreBinding::inflate
) {

    private var selectedTabTitle: String = ""

    private val viewModel: ExploreViewModel by viewModels {
        activity!!.applicationComponent.viewModelsFactory()
    }

    override fun updateViewModelData() {
    }

    override fun initUI() {
        val tlRecipesTypes = binding.root.findViewById<TabLayout>(R.id.tl_category)

        // до создания вкладок чтобы при создании первой вкладки оно сработало
        tlRecipesTypes.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                prepareTab(tab.text.toString())
                selectedTabTitle = tab.text.toString()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        // создаем для каждой категории рецепта вкладку
        for (category in viewModel.getCategories()) {
            tlRecipesTypes.addTab(
                tlRecipesTypes.newTab().setText(category)
            )
        }

        binding.root.findViewById<TextView>(R.id.tv_search).setOnClickListener {
            findNavController().navigate(
                ExploreFragmentDirections.actionFExploreToFSearchInput()
            )
        }
    }


    private val onOuterItemClickListener: (String) -> Unit = { label ->
        findNavController().navigate(
            ExploreFragmentDirections.actionFExploreToFSearchTarget(
                selectedTabTitle, label
            )
        )
    }
    private val onInnerItemClickListener: (String) -> Unit = { id ->
        findNavController().navigate(
            ExploreFragmentDirections.actionFExploreToFRecipeExtended(
                id
            )
        )
    }

    private val onReadyToLoadData: (
        ExploreInnerRecipesAdapter,
        Flow<PagingData<RecipeExplore>>
    ) -> Unit = { adapter, recipes ->
        lifecycleScope.launchWhenResumed {
            recipes.collectLatest(adapter::submitData)
        }
    }


    fun prepareTab(category: String) {
        val recyclerView = binding.root.findViewById<RecyclerView>(R.id.rv_explore_outer)
        val recyclerAdapter = ExploreOuterRecipesAdapter(
            binding.root.context,
            onOuterItemClickListener,
            onInnerItemClickListener,
            onReadyToLoadData
        )
        recyclerView.adapter = recyclerAdapter
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)

        lifecycleScope.launchWhenResumed {
            viewModel.getRecipes(category).collectLatest(recyclerAdapter::submitData)
        }
    }
}