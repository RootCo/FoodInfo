package com.example.foodinfo.ui

import android.widget.SearchView.OnQueryTextListener
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodinfo.databinding.FragmentSearchInputBinding
import com.example.foodinfo.ui.adapter.SearchInputAdapter
import com.example.foodinfo.utils.applicationComponent
import com.example.foodinfo.utils.hideKeyboard
import com.example.foodinfo.utils.showKeyboard
import com.example.foodinfo.view_model.SearchInputViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class SearchInputFragment : BaseFragment<FragmentSearchInputBinding>(
    FragmentSearchInputBinding::inflate
) {

    private val viewModel: SearchInputViewModel by viewModels {
        requireActivity().applicationComponent.viewModelsFactory()
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
            SearchInputFragmentDirections.actionFSearchInputToFSearchResult(text)
        )
    }

    private val onBackClickListener: () -> Unit = {
        findNavController().navigateUp()
    }

    private val onFilterClickListener: () -> Unit = {
        findNavController().navigate(
            SearchInputFragmentDirections.actionFSearchInputToFSearchFilter()
        )
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
                SearchInputFragmentDirections.actionFSearchInputToFSearchResult(query)
            )
            return false
        }
    }


    override fun initUI(): Unit = with(binding) {
        recyclerAdapter = SearchInputAdapter(
            requireContext(),
            onArrowClickListener,
            onItemClickListener
        )

        btnBack.setOnClickListener { onBackClickListener() }
        btnSearchFilter.setOnClickListener { onFilterClickListener() }

        with(rvSearchInput) {
            layoutManager = LinearLayoutManager(binding.root.context)
            adapter = recyclerAdapter
            setHasFixedSize(true)
            addOnScrollListener(onScrollStateListener)
        }
        etSearchInput.setOnQueryTextListener(onQueryChangedListener)
    }

    override fun subscribeUI() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.searchHistory.collectLatest(recyclerAdapter::submitList)
            }
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