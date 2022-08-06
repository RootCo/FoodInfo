package com.example.foodinfo.ui

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodinfo.R
import com.example.foodinfo.databinding.FragmentExploreBinding
import com.example.foodinfo.ui.adapter.ExploreOuterAdapter
import com.example.foodinfo.ui.decorator.ExploreOuterItemDecoration
import com.example.foodinfo.utils.applicationComponent
import com.example.foodinfo.view_model.ExploreViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class ExploreFragment : BaseFragment<FragmentExploreBinding>(
    FragmentExploreBinding::inflate
) {

    private val viewModel: ExploreViewModel by viewModels {
        activity!!.applicationComponent.viewModelsFactory()
    }

    private var submitDataJob: Job? = null
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


    override fun initUI(): Unit = with(binding) {
        recyclerAdapter = ExploreOuterAdapter(context!!, onInnerItemClickListener)
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
        }
    }

    override fun subscribeUI() {
        super.subscribeUI()
        submitDataJob = lifecycleScope.launch {
            recyclerAdapter.submitList(viewModel.categories)
        }
    }

    override fun unsubscribeUI() {
        submitDataJob?.cancel()
        submitDataJob = null
    }
}