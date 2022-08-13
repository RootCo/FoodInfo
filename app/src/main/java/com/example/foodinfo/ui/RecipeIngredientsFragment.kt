package com.example.foodinfo.ui

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodinfo.R
import com.example.foodinfo.databinding.FragmentRecipeIngredientsBinding
import com.example.foodinfo.ui.adapter.RecipeIngredientsAdapter
import com.example.foodinfo.ui.decorator.IngredientsItemDecoration
import com.example.foodinfo.utils.appComponent
import com.example.foodinfo.view_model.RecipeIngredientsViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class RecipeIngredientsFragment : BaseFragment<FragmentRecipeIngredientsBinding>(
    FragmentRecipeIngredientsBinding::inflate
) {

    private val args: RecipeIngredientsFragmentArgs by navArgs()

    private lateinit var recyclerAdapter: RecipeIngredientsAdapter

    private val viewModel: RecipeIngredientsViewModel by viewModels {
        requireActivity().appComponent.viewModelsFactory()
    }

    private val onBackClickListener: () -> Unit = {
        findNavController().navigateUp()
    }

    override fun initUI() {
        viewModel.recipeId = args.recipeId
        binding.btnBack.setOnClickListener { onBackClickListener() }

        recyclerAdapter = RecipeIngredientsAdapter(requireContext())

        with(binding.rvIngredients) {
            layoutManager = LinearLayoutManager(context)
            adapter = recyclerAdapter
            setHasFixedSize(true)
            addItemDecoration(
                IngredientsItemDecoration(
                    resources.getDimensionPixelSize(R.dimen.ingredients_item_space),
                    resources.getDimensionPixelSize(R.dimen.ingredients_item_margin)
                )
            )
        }
    }

    override fun subscribeUI() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.ingredients.collectLatest(recyclerAdapter::submitList)
            }
        }
    }
}