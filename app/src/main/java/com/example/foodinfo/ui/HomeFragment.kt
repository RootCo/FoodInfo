package com.example.foodinfo.ui

import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodinfo.R
import com.example.foodinfo.databinding.FragmentHomeBinding
import com.example.foodinfo.ui.adapter.HomeRecipesAdapter
import com.example.foodinfo.ui.decorator.HomeItemDecoration
import com.example.foodinfo.utils.applicationComponent
import com.example.foodinfo.view_model.HomeViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class HomeFragment : BaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::inflate
) {

    private val viewModel: HomeViewModel by viewModels {
        activity!!.applicationComponent.viewModelsFactory()
    }

    private var submitDataJob: Job? = null

    private val onItemClickListener: (String) -> Unit = { id ->
        findNavController().navigate(
            HomeFragmentDirections.actionFHomeToFRecipeExtended(id)
        )
    }


    override fun initUI() {
        val progressBar: ProgressBar
        val recyclerView: RecyclerView
        val recyclerAdapter: HomeRecipesAdapter


        with(binding.root) {
            progressBar = findViewById(R.id.home_progress)
            recyclerView = findViewById(R.id.rv_home_recipes)
            recyclerAdapter = HomeRecipesAdapter(context, onItemClickListener)
        }

        recyclerAdapter.addLoadStateListener { state: CombinedLoadStates ->
            recyclerView.isVisible = state.refresh != LoadState.Loading
            progressBar.isVisible = state.refresh == LoadState.Loading
        }

        with(recyclerView) {
            layoutManager = LinearLayoutManager(context).also {
                it.orientation = LinearLayoutManager.HORIZONTAL
            }
            adapter = recyclerAdapter
            setHasFixedSize(true)
            addItemDecoration(
                HomeItemDecoration(
                    resources.getDimensionPixelSize(R.dimen.home_recipes_space),
                    resources.getDimensionPixelSize(R.dimen.home_recipes_margin)
                )
            )
        }

        submitDataJob = lifecycleScope.launch {
            viewModel.recipes.collectLatest(recyclerAdapter::submitData)
        }
    }

    override fun releaseUI() {
        submitDataJob?.cancel()
        submitDataJob = null
    }
}