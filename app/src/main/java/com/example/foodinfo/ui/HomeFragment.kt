package com.example.foodinfo.ui

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodinfo.R
import com.example.foodinfo.databinding.FragmentHomeBinding
import com.example.foodinfo.ui.adapter.HomeCategoriesAdapter
import com.example.foodinfo.ui.decorator.ListHorizontalItemDecoration
import com.example.foodinfo.utils.appComponent
import com.example.foodinfo.utils.repeatOn
import com.example.foodinfo.view_model.HomeViewModel
import kotlinx.coroutines.flow.collectLatest


class HomeFragment : BaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::inflate
) {

    private val viewModel: HomeViewModel by viewModels {
        requireActivity().appComponent.viewModelsFactory()
    }

    private lateinit var recyclerAdapter: HomeCategoriesAdapter

    private val onItemClickListener: (String) -> Unit = { category ->
        findNavController().navigate(
            HomeFragmentDirections.actionFHomeToFSearchCategory(category)
        )
    }

    private val onSearchClickListener: () -> Unit = {
        findNavController().navigate(
            HomeFragmentDirections.actionFHomeToFSearchInput()
        )
    }


    override fun initUI() {
        recyclerAdapter = HomeCategoriesAdapter(
            requireContext(),
            onItemClickListener
        )
        binding.ivSearch.setOnClickListener { onSearchClickListener() }

        with(binding.rvCategories) {
            layoutManager = LinearLayoutManager(context).also {
                it.orientation = LinearLayoutManager.HORIZONTAL
            }
            adapter = recyclerAdapter
            addItemDecoration(
                ListHorizontalItemDecoration(
                    resources.getDimensionPixelSize(R.dimen.home_categories_space),
                    resources.getDimensionPixelSize(R.dimen.home_categories_margin)
                )
            )
            itemAnimator = null
        }

        binding.hintTop.textView.text = getString(
            R.string.TBD_screen,
            resources.getString(R.string.top_recipes_text)
        )
        binding.hintTrending.textView.text = getString(
            R.string.TBD_screen,
            resources.getString(R.string.trending_text)
        )
    }

    override fun subscribeUI() {
        repeatOn(Lifecycle.State.STARTED) {
            viewModel.categories.collectLatest(recyclerAdapter::submitList)
        }
    }
}