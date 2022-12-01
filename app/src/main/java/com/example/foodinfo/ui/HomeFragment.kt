package com.example.foodinfo.ui

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodinfo.R
import com.example.foodinfo.databinding.FragmentHomeBinding
import com.example.foodinfo.ui.adapter.HomeCategoriesAdapter
import com.example.foodinfo.ui.decorator.HomeCategoriesItemDecoration
import com.example.foodinfo.utils.appComponent
import com.example.foodinfo.utils.repeatOn
import com.example.foodinfo.view_model.HomeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class HomeFragment : BaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::inflate
) {

    private val viewModel: HomeViewModel by viewModels {
        requireActivity().appComponent.viewModelsFactory()
    }

    private lateinit var recyclerAdapter: HomeCategoriesAdapter

    private val onItemClickListener: (String) -> Unit = { category ->
        findNavController().navigate(
            HomeFragmentDirections.actionFHomeToFSearchCategory(category)
        )
    }

    private val onSearchClickListener: () -> Unit = {
        findNavController().navigate(
            HomeFragmentDirections.actionFHomeToFSearchInput()
        )
    }

    private val onFilterClickListener: () -> Unit = {
        findNavController().navigate(
            HomeFragmentDirections.actionFHomeToFSearchFilter()
        )
    }


    override fun initUI() {
        recyclerAdapter = HomeCategoriesAdapter(
            requireContext(),
            onItemClickListener
        )
        binding.llSearch.setOnClickListener { onSearchClickListener() }
        binding.btnFilter.setOnClickListener { onFilterClickListener() }

        with(binding.rvCategories) {
            layoutManager = LinearLayoutManager(context).also {
                it.orientation = LinearLayoutManager.HORIZONTAL
            }
            adapter = recyclerAdapter
            setHasFixedSize(true)
            addItemDecoration(
                HomeCategoriesItemDecoration(
                    resources.getDimensionPixelSize(R.dimen.home_categories_space),
                    resources.getDimensionPixelSize(R.dimen.home_categories_margin)
                )
            )
            itemAnimator = null
        }
    }

    override fun subscribeUI() {
        repeatOn(Lifecycle.State.STARTED) {
            recyclerAdapter.submitList(withContext(Dispatchers.IO) {
                viewModel.categories
            })
        }
    }
}