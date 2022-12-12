package com.example.foodinfo.ui

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.foodinfo.R
import com.example.foodinfo.databinding.FragmentRecipeIngredientsBinding
import com.example.foodinfo.ui.adapter.RecipeIngredientsAdapter
import com.example.foodinfo.ui.decorator.ListVerticalItemDecoration
import com.example.foodinfo.utils.appComponent
import com.example.foodinfo.utils.getMeasureSpacer
import com.example.foodinfo.utils.repeatOn
import com.example.foodinfo.view_model.RecipeIngredientsViewModel
import kotlinx.coroutines.flow.collectLatest


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

    private val onGetWeight: (Double) -> String = { weight ->
        getString(R.string.gram_float_value, weight)
    }

    private val onGetQuantity: (Double, String) -> String = { quantity, measure ->
        getString(
            R.string.float_measure_value,
            quantity,
            getMeasureSpacer(measure),
            measure
        )
    }


    override fun initUI() {
        viewModel.recipeId = args.recipeId
        binding.btnBack.setOnClickListener { onBackClickListener() }

        recyclerAdapter = RecipeIngredientsAdapter(
            requireContext(),
            onGetWeight,
            onGetQuantity
        )

        with(binding.rvIngredients) {
            adapter = recyclerAdapter
            setHasFixedSize(true)
            addItemDecoration(
                ListVerticalItemDecoration(
                    resources.getDimensionPixelSize(R.dimen.ingredients_item_space),
                    resources.getDimensionPixelSize(R.dimen.ingredients_item_margin)
                )
            )
        }
    }

    override fun subscribeUI() {
        repeatOn(Lifecycle.State.STARTED) {
            viewModel.ingredients.collectLatest(recyclerAdapter::submitList)
        }
    }
}