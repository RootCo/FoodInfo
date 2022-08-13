package com.example.foodinfo.ui

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
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
            ExploreFragmentDirections.actionFExploreToFSearchTarget(category, label)
        )
    }

    private val onSearchClickListener: () -> Unit = {
        findNavController().navigate(
            ExploreFragmentDirections.actionFExploreToFSearchInput()
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

    override fun initUI(): Unit = with(binding) {
        recyclerAdapter = ExploreOuterAdapter(requireContext(), onInnerItemClickListener)
        tvSearch.setOnClickListener { onSearchClickListener() }
        with(rvCategories) {
            layoutManager = LinearLayoutManager(context)
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

    override fun subscribeUI(): Unit = with(binding) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                withContext(Dispatchers.IO) {
                    recyclerAdapter.submitList(viewModel.categories)
                }
                withContext(Dispatchers.Main) {
                    rvCategories.restoreState(viewModel.scrollState)
                }
            }
        }
    }
}