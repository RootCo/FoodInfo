package com.example.foodinfo.ui

import android.widget.ImageView
import android.widget.SearchView.OnQueryTextListener
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
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


class SearchInputFragment : BaseDataFragment<FragmentSearchInputBinding>(
    FragmentSearchInputBinding::inflate
) {

    private val viewModel: SearchInputViewModel by viewModels {
        activity!!.applicationComponent.viewModelsFactory()
    }

    override fun updateViewModelData() {
        viewModel.updateSearchHistory()
    }

    override fun initUI() {
        val recyclerView = binding.root.findViewById<RecyclerView>(R.id.rv_search_input)
        val recyclerAdapter = SearchInputAdapter(
            binding.root.context, onArrowClickListener, onItemClickListener
        )
        recyclerView.layoutManager = LinearLayoutManager(binding.root.context)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = recyclerAdapter

        /*
            тут без handleNoData т.к. фрагмент может функционировать и без
            истории поиска, её просто не будет и всё
         */
        viewModel.searchHistory.observe(viewLifecycleOwner) { searchHistory ->
            searchHistory?.let {
                recyclerAdapter.submitList(it)
            }
        }

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    hideKeyboard()
                    return
                }
            }
        })

        binding.root.findViewById<ImageView>(R.id.btn_back).setOnClickListener {
            findNavController().navigateUp()
        }

        binding.root.findViewById<ImageView>(R.id.btn_search_filter).setOnClickListener {
            findNavController().navigate(
                SearchInputFragmentDirections.actionFSearchInputToFSearchFilter()
            )
        }

        binding.root.findViewById<SearchView>(R.id.et_search_input)
            .setOnQueryTextListener(object : OnQueryTextListener,
                SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String): Boolean {
                    viewModel.updateSearchHistory(newText)
                    return false
                }

                override fun onQueryTextSubmit(query: String): Boolean {
                    viewModel.addToHistory(query)
                    findNavController().navigate(
                        SearchInputFragmentDirections.actionFSearchInputToFSearchResult(
                            query
                        )
                    )
                    return false
                }
            })
    }


    private val onArrowClickListener: (String) -> Unit = { text ->
        val searchView = binding.root.findViewById<SearchView>(R.id.et_search_input)
        searchView.setQuery(text, false)
        showKeyboard(searchView)
    }
    private val onItemClickListener: (String) -> Unit = { text ->
        findNavController().navigate(
            SearchInputFragmentDirections.actionFSearchInputToFSearchResult(
                text
            )
        )
    }


    override fun onResume() {
        super.onResume()
        showKeyboard(binding.root.findViewById<SearchView>(R.id.et_search_input))
    }

    override fun onPause() {
        hideKeyboard()
        super.onPause()
    }
}