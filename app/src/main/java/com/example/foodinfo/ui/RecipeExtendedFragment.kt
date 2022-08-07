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

                    tvServingsValue.text = recipe.servings.toString()
                    tvWeightValue.text = recipe.totalWeight.toString() + "g"
                    tvTimeValue.text = recipe.totalTime.toString() + " min"

                    tvProteinValue.text = recipe.protein.toString() + "g"
                    tvCarbValue.text = recipe.carb.toString() + "g"
                    tvFatValue.text = recipe.fat.toString() + "g"

                    pbProtein.progress = recipe.protein
                    pbCarb.progress = recipe.carb
                    pbFat.progress = recipe.fat

                    tvCaloriesValue.text = recipe.calories.toString()
                    pbCalories.progress = recipe.calories * 100 / 2500
                    tvCaloriesPercent.text =
                        (recipe.calories * 100 / 2500).toString() + "%"


                    // this is totally garbage
                    for (mealType in recipe.mealType) {
                        cgMeal.addView(createChip(mealType.label))
                    }
                    for (dishType in recipe.dishType) {
                        cgDish.addView(createChip(dishType.label))
                    }
                    for (dietType in recipe.dietType) {
                        cgDiet.addView(createChip(dietType.label))
                    }
                    for (healthType in recipe.healthType) {
                        cgHealth.addView(createChip(healthType.label))
                    }
                    for (cuisineType in recipe.cuisineType) {
                        cgCuisine.addView(createChip(cuisineType.label))
                    }
                }
            }
        }
    }

    private fun createChip(text: String): Chip {
        return Chip(ContextThemeWrapper(context, R.style.Chip)).also { it.text = text }
    }
}