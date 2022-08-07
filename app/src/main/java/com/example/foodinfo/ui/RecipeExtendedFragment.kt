package com.example.foodinfo.ui

import android.view.ContextThemeWrapper
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.foodinfo.R
import com.example.foodinfo.databinding.FragmentRecipeExtendedBinding
import com.example.foodinfo.utils.applicationComponent
import com.example.foodinfo.view_model.RecipeExtendedViewModel
import com.google.android.material.chip.Chip
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class RecipeExtendedFragment : BaseFragment<FragmentRecipeExtendedBinding>(
    FragmentRecipeExtendedBinding::inflate
) {

    private val args: RecipeExtendedFragmentArgs by navArgs()

    private val viewModel: RecipeExtendedViewModel by viewModels {
        activity!!.applicationComponent.viewModelsFactory()
    }

    private val onBackClickListener: () -> Unit = {
        findNavController().navigateUp()
    }


    override fun initUI(): Unit = with(binding) {
        viewModel.recipeId = args.recipeId
        btnBack.setOnClickListener { onBackClickListener() }
    }

    // this is garbage
    override fun subscribeUI(): Unit = with(binding) {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.recipe.collectLatest { recipe ->
                    tvRecipeName.text = recipe.name
                    Glide.with(ivRecipePreview.context)
                        .load(recipe.preview)
                        .into(ivRecipePreview)

                    tvServingsValue.text = recipe.servings
                    tvWeightValue.text = recipe.totalWeight
                    tvTimeValue.text = recipe.totalTime

                    tvProteinValue.text = recipe.protein
                    tvCarbValue.text = recipe.carb
                    tvFatValue.text = recipe.fat

                    pbProtein.progress = recipe.proteinDaily
                    pbCarb.progress = recipe.carbDaily
                    pbFat.progress = recipe.fatDaily

                    tvCaloriesValue.text = recipe.calories
                    pbCalories.progress = recipe.caloriesDaily
                    tvCaloriesPercent.text = recipe.caloriesDaily.toString() + "%"

                    // this is totally garbage
                    for (label in recipe.mealType) {
                        cgMeal.addView(createChip(label))
                    }
                    for (label in recipe.dishType) {
                        cgDish.addView(createChip(label))
                    }
                    for (label in recipe.dietType) {
                        cgDiet.addView(createChip(label))
                    }
                    for (label in recipe.healthType) {
                        cgHealth.addView(createChip(label))
                    }
                    for (label in recipe.cuisineType) {
                        cgCuisine.addView(createChip(label))
                    }
                }
            }
        }
    }

    private fun createChip(text: String): Chip {
        return Chip(ContextThemeWrapper(context, R.style.Chip)).also { it.text = text }
    }
}