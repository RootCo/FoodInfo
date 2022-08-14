package com.example.foodinfo.ui

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodinfo.R
import com.example.foodinfo.databinding.FragmentRecipeNutrientsBinding
import com.example.foodinfo.ui.adapter.RecipeNutrientsAdapter
import com.example.foodinfo.ui.decorator.NutrientsItemDecoration
import com.example.foodinfo.utils.appComponent
import com.example.foodinfo.view_model.RecipeNutrientsViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class RecipeNutrientsFragment : BaseFragment<FragmentRecipeNutrientsBinding>(
    FragmentRecipeNutrientsBinding::inflate
) {

    private val args: RecipeNutrientsFragmentArgs by navArgs()

    private lateinit var recyclerAdapter: RecipeNutrientsAdapter

    private val viewModel: RecipeNutrientsViewModel by viewModels {
        requireActivity().appComponent.viewModelsFactory()
    }

    private val onBackClickListener: () -> Unit = {
        findNavController().navigateUp()
    }

    private val onGetNutrientWeight: (
        Double, Double, String
    ) -> String = { totalWeight, dailyWeight, measure ->
        getString(
            R.string.rv_item_nutrient_value,
            totalWeight,
            dailyWeight,
            measure
        )
    }

    private val onGetNutrientPercent: (Int) -> String = { dailyPercent ->
        getString(
            R.string.percent_value,
            dailyPercent
        )
    }


    override fun initUI() {
        viewModel.recipeId = args.recipeId
        binding.btnBack.setOnClickListener { onBackClickListener() }

        recyclerAdapter = RecipeNutrientsAdapter(
            requireContext(),
            onGetNutrientWeight,
            onGetNutrientPercent
        )

        with(binding.rvIngredients) {
            layoutManager = LinearLayoutManager(context)
            adapter = recyclerAdapter
            setHasFixedSize(true)
            addItemDecoration(
                NutrientsItemDecoration(
                    resources.getDimensionPixelSize(R.dimen.nutrients_item_space),
                    resources.getDimensionPixelSize(R.dimen.nutrients_item_margin)
                )
            )
        }
    }

    override fun subscribeUI() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.nutrients.collectLatest(recyclerAdapter::submitList)
            }
        }
    }
}