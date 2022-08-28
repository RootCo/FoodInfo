package com.example.foodinfo.ui

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.foodinfo.R
import com.example.foodinfo.databinding.FragmentExploreBinding
import com.example.foodinfo.ui.adapter.ExploreOuterAdapter
import com.example.foodinfo.ui.decorator.ExploreOuterItemDecoration
import com.example.foodinfo.utils.appComponent
import com.example.foodinfo.utils.getState
import com.example.foodinfo.utils.restoreState
import com.example.foodinfo.view_model.ExploreViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ExploreFragment : BaseFragment<FragmentExploreBinding>(
    FragmentExploreBinding::inflate
) {

    private val viewModel: ExploreViewModel by viewModels {
        requireActivity().appComponent.viewModelsFactory()
    }

    private lateinit var recyclerAdapter: ExploreOuterAdapter

    private val onInnerItemClickListener: (String, String) -> Unit = { category, label ->
        findNavController().navigate(
            ExploreFragmentDirections.actionFExploreToFSearchLabel(category, label)
        )
    }

    private val onSearchClickListener: () -> Unit = {
        findNavController().navigate(
            ExploreFragmentDirections.actionFExploreToFSearchInput()
        )
    }

    private val onFilterClickListener: () -> Unit = {
        findNavController().navigate(
            ExploreFragmentDirections.actionFExploreToFSearchFilter()
        )
    }

    private val onScrollStateListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                with(binding) {
                    viewModel.scrollState = rvCategories.getState()
                }
            }
        }
    }

    override fun initUI() {
        recyclerAdapter = ExploreOuterAdapter(requireContext(), onInnerItemClickListener)
        binding.llSearch.setOnClickListener { onSearchClickListener() }
        binding.btnFilter.setOnClickListener { onFilterClickListener() }
        with(binding.rvCategories) {
            adapter = recyclerAdapter
            setHasFixedSize(true)
            addItemDecoration(
                ExploreOuterItemDecoration(
                    resources.getDimensionPixelSize(R.dimen.explore_item_outer_space),
                    resources.getDimensionPixelSize(R.dimen.explore_item_outer_margin)
                )
            )
            addOnScrollListener(onScrollStateListener)
        }
    }

    override fun subscribeUI() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                withContext(Dispatchers.IO) {
                    recyclerAdapter.submitList(viewModel.categories)
                }
                withContext(Dispatchers.Main) {
                    binding.rvCategories.restoreState(viewModel.scrollState)
                }
            }
        }
    }
}