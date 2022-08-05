package com.example.foodinfo.ui

import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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


    override fun initUI() {
        val recyclerAdapter: ExploreOuterAdapter
        val recyclerView: RecyclerView
        val progressBar: ProgressBar
        val searchView: TextView

        with(binding.root) {
            recyclerAdapter = ExploreOuterAdapter(context, onInnerItemClickListener)
            recyclerView = findViewById(R.id.rv_explore_outer)
            progressBar = findViewById(R.id.explore_progress)
            searchView = findViewById(R.id.tv_search)
        }

        searchView.setOnClickListener { onSearchClickListener() }

        with(recyclerView) {
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

        submitDataJob = lifecycleScope.launch {
            recyclerAdapter.submitList(viewModel.categories)
        }
    }

    override fun releaseUI() {
        submitDataJob?.cancel()
        submitDataJob = null
    }
}