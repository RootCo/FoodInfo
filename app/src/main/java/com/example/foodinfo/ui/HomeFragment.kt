package com.example.foodinfo.ui

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodinfo.R
import com.example.foodinfo.databinding.FragmentHomeBinding
import com.example.foodinfo.ui.adapter.HomeRecipesAdapter
import com.example.foodinfo.ui.decorator.HomeItemDecoration
import com.example.foodinfo.utils.applicationComponent
import com.example.foodinfo.view_model.HomeViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class HomeFragment : BaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::inflate
) {

    private val viewModel: HomeViewModel by viewModels {
        activity!!.applicationComponent.viewModelsFactory()
    }

    private lateinit var recyclerAdapter: HomeRecipesAdapter

    private val onItemClickListener: (String) -> Unit = { id ->
        findNavController().navigate(
            HomeFragmentDirections.actionFHomeToFRecipeExtended(id)
        )
    }


    override fun initUI(): Unit = with(binding) {
        recyclerAdapter = HomeRecipesAdapter(context!!, onItemClickListener).also {
            it.addLoadStateListener { state: CombinedLoadStates ->
                rvRecipes.isVisible = state.refresh != LoadState.Loading
                pbRecipes.isVisible = state.refresh == LoadState.Loading
            }
        }

        with(rvRecipes) {
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
    }

    override fun subscribeUI() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.recipes.collectLatest(recyclerAdapter::submitData)
            }
        }
    }
}