package com.example.foodinfo.ui

import android.widget.SearchView.OnQueryTextListener
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.foodinfo.databinding.FragmentSearchInputBinding
import com.example.foodinfo.ui.adapter.SearchInputAdapter
import com.example.foodinfo.utils.appComponent
import com.example.foodinfo.utils.hideKeyboard
import com.example.foodinfo.utils.repeatOn
import com.example.foodinfo.utils.showKeyboard
import com.example.foodinfo.view_model.SearchInputViewModel
import kotlinx.coroutines.flow.collectLatest


class SearchInputFragment : BaseFragment<FragmentSearchInputBinding>(
    FragmentSearchInputBinding::inflate
) {

    private val viewModel: SearchInputViewModel by viewModels {
        requireActivity().appComponent.viewModelsFactory()
    }

    private lateinit var recyclerAdapter: SearchInputAdapter

    private val onArrowClickListener: (String) -> Unit = { text ->
        with(binding) {
            etSearchInput.setQuery(text, false)
            showKeyboard(etSearchInput)
        }
    }

    private val onItemClickListener: (String) -> Unit = { text ->
        viewModel.addToHistory(text)
        findNavController().navigate(
            SearchInputFragmentDirections.actionFSearchInputToFSearchQuery(text)
        )
    }

    private val onFilterClickListener: () -> Unit = {
        findNavController().navigate(
            SearchInputFragmentDirections.actionFSearchInputToFSearchFilter()
        )
    }

    private val onBackClickListener: () -> Unit = {
        findNavController().navigateUp()
    }

    private val onScrollStateListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                hideKeyboard()
            }
        }
    }

    private val onQueryChangedListener = object : OnQueryTextListener,
        SearchView.OnQueryTextListener {
        override fun onQueryTextChange(newText: String): Boolean {
            viewModel.updateSearchHistory(newText)
            return false
        }

        override fun onQueryTextSubmit(query: String): Boolean {
            viewModel.addToHistory(query)
            findNavController().navigate(
                SearchInputFragmentDirections.actionFSearchInputToFSearchQuery(query)
            )
            return false
        }
    }


    override fun initUI() {
        recyclerAdapter = SearchInputAdapter(
            requireContext(),
            onArrowClickListener,
            onItemClickListener
        )

        binding.btnBack.setOnClickListener { onBackClickListener() }
        binding.btnFilter.setOnClickListener { onFilterClickListener() }

        with(binding.rvSearchInput) {
            adapter = recyclerAdapter
            setHasFixedSize(true)
            addOnScrollListener(onScrollStateListener)
        }
        binding.etSearchInput.setOnQueryTextListener(onQueryChangedListener)
    }

    override fun subscribeUI() {
        repeatOn(Lifecycle.State.STARTED) {
            viewModel.searchHistory.collectLatest(recyclerAdapter::submitList)
        }
    }


    override fun onResume() {
        super.onResume()
        with(binding) {
            showKeyboard(etSearchInput)
        }
    }

    override fun onPause() {
        hideKeyboard()
        super.onPause()
    }
}