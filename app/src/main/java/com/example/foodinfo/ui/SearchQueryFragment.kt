package com.example.foodinfo.ui

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.foodinfo.R
import com.example.foodinfo.databinding.FragmentSearchQueryBinding
import com.example.foodinfo.ui.adapter.SearchRecipeAdapter
import com.example.foodinfo.ui.decorator.SearchRecipeItemDecoration
import com.example.foodinfo.utils.appComponent
import com.example.foodinfo.utils.repeatOn
import com.example.foodinfo.view_model.SearchQueryViewModel
import kotlinx.coroutines.flow.collectLatest


class SearchQueryFragment : BaseFragment<FragmentSearchQueryBinding>(
    FragmentSearchQueryBinding::inflate
) {

    private val args: SearchQueryFragmentArgs by navArgs()

    private val viewModel: SearchQueryViewModel by viewModels {
        requireActivity().appComponent.viewModelsFactory()
    }

    private lateinit var recyclerAdapter: SearchRecipeAdapter

    // возвращаемся на предыдущий экран минуя экран с вводом поиска
    private val onBackClickListener: () -> Unit = {
        findNavController().popBackStack(R.id.f_search_input, true)
    }

    private val onSearchClickListener: () -> Unit = {
        findNavController().navigate(
            SearchQueryFragmentDirections.actionFSearchQueryToFSearchInput()
        )
    }

    private val onItemClickListener: (String) -> Unit = { id ->
        findNavController().navigate(
            SearchQueryFragmentDirections.actionFSearchQueryToFRecipeExtended(id)
        )
    }

    private val onFavoriteClickListener: (String) -> Unit = { id ->
        viewModel.updateFavoriteMark(id)
    }

    private val onGetTime: (Int) -> String = { time ->
        getString(R.string.time_value, time)
    }


    override fun initUI() {
        viewModel.setFilter(args.query)

        binding.tvQuery.text = args.query
        binding.btnBack.setOnClickListener { onBackClickListener() }
        binding.btnSearch.setOnClickListener { onSearchClickListener() }

        recyclerAdapter = SearchRecipeAdapter(
            requireContext(),
            onGetTime,
            onItemClickListener,
            onFavoriteClickListener
        )

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
        repeatOn(Lifecycle.State.STARTED) {
            viewModel.recipes.collectLatest(recyclerAdapter::submitData)
        }
    }
}