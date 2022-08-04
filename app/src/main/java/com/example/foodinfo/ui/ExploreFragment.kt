package com.example.foodinfo.ui

import android.view.LayoutInflater
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodinfo.R
import com.example.foodinfo.databinding.FragmentExploreBinding
import com.example.foodinfo.ui.decorator.ExploreItemDecoration
import com.example.foodinfo.utils.applicationComponent
import com.example.foodinfo.view_model.ExploreViewModel
import com.google.android.material.tabs.TabLayout


class ExploreFragment : BaseDataFragment<FragmentExploreBinding>(
    FragmentExploreBinding::inflate
) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var categoryTabs: TabLayout

    private val viewModel: ExploreViewModel by viewModels {
        activity!!.applicationComponent.viewModelsFactory()
    }

    override fun updateViewModelData() {
    }

    override fun initUI() {
        viewModel.initAdapters(context!!, onItemClickListener)

        recyclerView = binding.root.findViewById(R.id.rv_explore_outer)
        categoryTabs = binding.root.findViewById(R.id.tl_category)

        with(recyclerView) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            addItemDecoration(
                ExploreItemDecoration(
                    resources.getDimensionPixelSize(R.dimen.explore_item_space),
                    resources.getDimensionPixelSize(R.dimen.explore_item_margin)
                )
            )
        }

        with(LayoutInflater.from(context)) {
            for (entry in viewModel.categories.entries) {
                categoryTabs.addTab(
                    categoryTabs.newTab()
                        .setCustomView(inflate(R.layout.tab_layout, null, false))
                        .setText(entry.value.label)
                        .setId(entry.key)
                )
            }
        }

        categoryTabs.getTabAt(viewModel.tabIndex)!!.also { tab ->
            tab.select()
            viewModel.updateTab(tab.id, recyclerView)
        }

        categoryTabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewModel.updateTab(tab.id, recyclerView)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })


        binding.root.findViewById<TextView>(R.id.tv_search).setOnClickListener {
            findNavController().navigate(
                ExploreFragmentDirections.actionFExploreToFSearchInput()
            )
        }
    }

    private val onItemClickListener: (String, String) -> Unit = { category, label ->
        findNavController().navigate(
            ExploreFragmentDirections.actionFExploreToFSearchTarget(category, label)
        )
    }
}