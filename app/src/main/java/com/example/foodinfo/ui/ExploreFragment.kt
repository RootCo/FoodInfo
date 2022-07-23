package com.example.foodinfo.ui

import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodinfo.R
import com.example.foodinfo.databinding.FragmentExploreBinding
import com.example.foodinfo.utils.applicationComponent
import com.example.foodinfo.view_model.ExploreViewModel
import com.google.android.material.tabs.TabLayout


class ExploreFragment : BaseDataFragment<FragmentExploreBinding>(
    FragmentExploreBinding::inflate
) {

    private lateinit var recyclerView: RecyclerView

    private val viewModel: ExploreViewModel by viewModels {
        activity!!.applicationComponent.viewModelsFactory()
    }

    override fun updateViewModelData() {
    }

    override fun initUI() {
        viewModel.initAdapters(onInnerItemClickListener, onOuterItemClickListener)

        recyclerView = binding.root.findViewById(R.id.rv_explore_outer)
        recyclerView.setHasFixedSize(true)
        recyclerView.setItemViewCacheSize(20)
        recyclerView.layoutManager = LinearLayoutManager(context).also {
            it.initialPrefetchItemCount = 3
        }

        val categoryTabs = binding.root.findViewById<TabLayout>(R.id.tl_category)

        categoryTabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewModel.updateTab(tab)
                recyclerView.adapter = viewModel.adapter
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
}