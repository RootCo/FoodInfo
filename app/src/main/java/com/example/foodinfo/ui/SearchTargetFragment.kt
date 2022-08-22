package com.example.foodinfo.ui

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.example.foodinfo.R
import com.example.foodinfo.databinding.FragmentSearchTargetBinding
import com.example.foodinfo.ui.adapter.SearchTargetAdapter
import com.example.foodinfo.ui.decorator.SearchTargetItemDecoration
import com.example.foodinfo.utils.appComponent
import com.example.foodinfo.utils.showDescriptionDialog
import com.example.foodinfo.view_model.SearchTargetViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchTargetFragment : BaseFragment<FragmentSearchTargetBinding>(
    FragmentSearchTargetBinding::inflate
) {

    private val args: SearchTargetFragmentArgs by navArgs()

    private lateinit var recyclerAdapter: SearchTargetAdapter

    private val viewModel: SearchTargetViewModel by viewModels {
        requireActivity().appComponent.viewModelsFactory()
    }

    private val onBackClickListener: () -> Unit = {
        findNavController().navigateUp()
    }

    private val onSearchClickListener: () -> Unit = {
        findNavController().navigate(
            SearchTargetFragmentDirections.actionFSearchTargetToFSearchInput()
        )
    }

    private val onLabelClickListener: () -> Unit = {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            val labelItem = viewModel.getLabel(args.category, args.label)
            withContext(Dispatchers.Main) {
                showDescriptionDialog(
                    labelItem.label,
                    labelItem.description,
                    labelItem.preview
                )
            }
        }
    }

    private val onItemClickListener: (String) -> Unit = { id ->
        findNavController().navigate(
            SearchTargetFragmentDirections.actionFSearchTargetToFRecipeExtended(id)
        )
    }

    private val onFavoriteClickListener: (String) -> Unit = { id ->
        viewModel.updateFavoriteMark(id)
    }

    private val onGetTime: (Int) -> String = { time ->
        getString(R.string.time_value, time)
    }


    override fun initUI(): Unit = with(binding) {
        viewModel.setFilter(args.category, args.label)

        tvLabel.text = args.label
        tvLabel.setOnClickListener { onLabelClickListener() }
        btnBack.setOnClickListener { onBackClickListener() }
        btnSearch.setOnClickListener { onSearchClickListener() }

        recyclerAdapter = SearchTargetAdapter(
            requireContext(),
            onGetTime,
            onItemClickListener,
            onFavoriteClickListener
        ).also {
            it.addLoadStateListener { state: CombinedLoadStates ->
                rvRecipes.isVisible = state.refresh != LoadState.Loading
                pbRecipes.isVisible = state.refresh == LoadState.Loading
            }
        }

        with(rvRecipes) {
            adapter = recyclerAdapter
            setHasFixedSize(true)
            addItemDecoration(
                SearchTargetItemDecoration(
                    resources.getDimensionPixelSize(R.dimen.search_target_item_horizontal),
                    resources.getDimensionPixelSize(R.dimen.search_target_item_vertical),
                    2
                )
            )
        }
    }

    override fun subscribeUI() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.recipes.collectLatest(recyclerAdapter::submitData)
            }
        }
    }
}