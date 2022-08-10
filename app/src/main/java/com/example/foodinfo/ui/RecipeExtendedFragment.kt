package com.example.foodinfo.ui

import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
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
import com.google.android.material.chip.ChipGroup
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

    override fun subscribeUI(): Unit = with(binding) {
        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                pbContent.isVisible = true
                svContent.isVisible = false

                viewModel.recipe.collectLatest { recipe ->
                    tvRecipeName.text = recipe.name
                    Glide.with(ivRecipePreview.context)
                        .load(recipe.previewURL)
                        .placeholder(R.drawable.ic_image_placeholder)
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
                    tvCaloriesPercent.text = "${recipe.caloriesDaily}%"

                    llCategories.removeAllViews()
                    with(LayoutInflater.from(context)) {
                        for (category in recipe.categories.entries) {
                            llCategories.addView(createCategory(category, this))
                        }
                    }

                    pbContent.isVisible = false
                    svContent.isVisible = true
                }
            }
        }
    }

    private fun createCategory(
        category: Map.Entry<String, List<String>>,
        inflater: LayoutInflater
    ): View {
        val categoryView = inflater.inflate(R.layout.item_extended_category, null, false)
        val categoryTitle = categoryView.findViewById<TextView>(R.id.tv_title)
        val categoryLabels = categoryView.findViewById<ChipGroup>(R.id.cg_labels)

        categoryTitle.text = category.key
        for (label in category.value) {
            categoryLabels.addView(createLabel(label))
        }

        return categoryView
    }

    private fun createLabel(label: String): Chip {
        return Chip(ContextThemeWrapper(context, R.style.Chip)).also { it.text = label }
    }
}