package com.example.foodinfo.ui

import android.widget.ImageView
import android.widget.SearchView.OnQueryTextListener
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodinfo.R
import com.example.foodinfo.databinding.FragmentSearchInputBinding
import com.example.foodinfo.ui.adapter.SearchInputAdapter
import com.example.foodinfo.utils.applicationComponent
import com.example.foodinfo.utils.hideKeyboard
import com.example.foodinfo.utils.showKeyboard
import com.example.foodinfo.view_model.SearchInputViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class SearchInputFragment : BaseFragment<FragmentSearchInputBinding>(
    FragmentSearchInputBinding::inflate
) {

    private val viewModel: SearchInputViewModel by viewModels {
        activity!!.applicationComponent.viewModelsFactory()
    }

    private var submitDataJob: Job? = null
    private var searchView: SearchView? = null

    private val onArrowClickListener: (String) -> Unit = { text ->
        searchView?.let { searchView ->
            searchView.setQuery(text, false)
            showKeyboard(searchView)
        }
    }

    private val onItemClickListener: (String) -> Unit = { text ->
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


    override fun initUI() {
        val buttonBack: ImageView
        val buttonFilter: ImageView
        val recyclerView: RecyclerView
        val recyclerAdapter: SearchInputAdapter

        with(binding.root) {
            recyclerAdapter = SearchInputAdapter(
                context,
                onArrowClickListener,
                onItemClickListener
            )
            searchView = findViewById(R.id.et_search_input)
            buttonBack = findViewById(R.id.btn_back)
            buttonFilter = findViewById(R.id.btn_search_filter)
            recyclerView = findViewById(R.id.rv_search_input)
        }

        buttonBack.setOnClickListener { onBackClickListener() }
        buttonFilter.setOnClickListener { onFilterClickListener }

        with(recyclerView) {
            layoutManager = LinearLayoutManager(binding.root.context)
            adapter = recyclerAdapter
            setHasFixedSize(true)
            addOnScrollListener(onScrollStateListener)
        }

        searchView!!.setOnQueryTextListener(onQueryChangedListener)

        submitDataJob = lifecycleScope.launch {
            viewModel.searchHistory.collectLatest(recyclerAdapter::submitList)
        }
    }

    override fun releaseUI() {
        submitDataJob?.cancel()
        submitDataJob = null
        searchView = null
    }


    override fun onResume() {
        super.onResume()
        showKeyboard(searchView!!)
    }

    override fun onPause() {
        hideKeyboard()
        super.onPause()
    }
}