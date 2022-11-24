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
import androidx.recyclerview.widget.GridLayoutManager
import com.example.foodinfo.R
import com.example.foodinfo.databinding.FragmentSearchLabelBinding
import com.example.foodinfo.ui.adapter.SearchRecipeAdapter
import com.example.foodinfo.ui.decorator.SearchRecipeItemDecoration
import com.example.foodinfo.utils.appComponent
import com.example.foodinfo.utils.showDescriptionDialog
import com.example.foodinfo.view_model.SearchLabelViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class SearchLabelFragment : BaseFragment<FragmentSearchLabelBinding>(
    FragmentSearchLabelBinding::inflate
) {

    private val args: SearchLabelFragmentArgs by navArgs()

    private lateinit var recyclerAdapter: SearchRecipeAdapter

    private val viewModel: SearchLabelViewModel by viewModels {
        requireActivity().appComponent.viewModelsFactory()
    }

    private val onBackClickListener: () -> Unit = {
        findNavController().navigateUp()
    }

    private val onSearchClickListener: () -> Unit = {
        findNavController().navigate(
            SearchLabelFragmentDirections.actionFSearchLabelToFSearchInput()
        )
    }

    private val onHeaderClickListener: () -> Unit = {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            val labelItem = viewModel.label
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
            SearchLabelFragmentDirections.actionFSearchLabelToFRecipeExtended(id)
        )
    }

    private val onFavoriteClickListener: (String) -> Unit = { id ->
        viewModel.updateFavoriteMark(id)
    }

    private val onGetTime: (Int) -> String = { time ->
        getString(R.string.time_value, time)
    }


    override fun initUI() {
        viewModel.setLabel(args.category, args.label)

        binding.tvLabel.text = args.label
        binding.tvLabel.setOnClickListener { onHeaderClickListener() }
        binding.btnBack.setOnClickListener { onBackClickListener() }
        binding.btnSearch.setOnClickListener { onSearchClickListener() }

        recyclerAdapter = SearchRecipeAdapter(
            requireContext(),
            onGetTime,
            onItemClickListener,
            onFavoriteClickListener
        ).also {

            // this part cause flickering when user adds recipe to favorites (operation is
            // too fast, recycler appears a few milliseconds after disappearing)
            // don't know how to fix so simply disabled it temporary

//            it.addLoadStateListener { state: CombinedLoadStates ->
//                binding.rvRecipes.isVisible = state.refresh != LoadState.Loading
//                binding.pbRecipes.isVisible = state.refresh == LoadState.Loading
//            }
        }

        with(binding.rvRecipes) {
            adapter = recyclerAdapter
            setHasFixedSize(true)
            addItemDecoration(
                SearchRecipeItemDecoration(
                    resources.getDimensionPixelSize(R.dimen.search_recipes_item_horizontal),
                    resources.getDimensionPixelSize(R.dimen.search_recipes_item_vertical),
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