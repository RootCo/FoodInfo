package com.example.foodinfo.ui

import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodinfo.R
import com.example.foodinfo.databinding.FragmentExploreBinding
import com.example.foodinfo.ui.adapter.ExploreOuterRecipesAdapter
import com.example.foodinfo.utils.applicationComponent
import com.example.foodinfo.utils.handleNoData
import com.example.foodinfo.view_model.ExploreViewModel
import com.google.android.material.tabs.TabLayout


class ExploreFragment : BaseDataFragment<FragmentExploreBinding>(
    FragmentExploreBinding::inflate
) {

    private var selectedTabTitle: String = ""

    private val viewModel: ExploreViewModel by viewModels {
        activity!!.applicationComponent.viewModelsFactory()
    }

    override fun updateViewModelData() {
        viewModel.updateRecipes()
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
        for (category in viewModel.categories) {
            tlRecipesTypes.addTab(
                tlRecipesTypes.newTab().setText(category.name)
            )
        }

        binding.root.findViewById<TextView>(R.id.tv_search).setOnClickListener {
            findNavController().navigate(R.id.action_f_explore_to_f_search_input)
        }
    }


    private val onOuterItemClickListener: (String) -> Unit = { label ->
        val action = ExploreFragmentDirections.actionFExploreToFSearchTarget(
            selectedTabTitle, label
        )
        findNavController().navigate(action)
    }
    private val onInnerItemClickListener: (String) -> Unit = { id ->
        val action = ExploreFragmentDirections.actionFExploreToFRecipeExtended(id)
        findNavController().navigate(action)
    }


    fun prepareTab(tab: String) {
        val recyclerView = binding.root.findViewById<RecyclerView>(R.id.rv_explore_outer)
        val recyclerAdapter = ExploreOuterRecipesAdapter(
            binding.root.context, onOuterItemClickListener, onInnerItemClickListener
        )
        recyclerView.adapter = recyclerAdapter
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.recipes.observe(viewLifecycleOwner) { recipes ->
            when (recipes != null) {
                true  -> recyclerAdapter.setContent(recipes[tab]!!)
                false -> handleNoData()
            }
        }
    }
}