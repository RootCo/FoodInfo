package com.example.foodinfo.ui

import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
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
        requireActivity().applicationComponent.viewModelsFactory()
    }

    private val onBackClickListener: () -> Unit = {
        findNavController().navigateUp()
    }


    override fun initUI() {
        viewModel.recipeId = args.recipeId
        binding.btnBack.setOnClickListener { onBackClickListener() }
    }

    override fun subscribeUI(): Unit = with(binding) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.recipe.collectLatest { recipe ->
                    tvRecipeName.text = recipe.name
                    Glide.with(ivRecipePreview.context)
                        .load(recipe.previewURL)
                        .error(R.drawable.ic_no_image)
                        .placeholder(null)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(ivRecipePreview)

                    tvServingsValue.text = recipe.servings
                    tvWeightValue.text = recipe.totalWeight
                    tvTimeValue.text = recipe.totalTime

                    tvCaloriesValue.text = recipe.calories
                    pbCalories.progress = recipe.caloriesDaily
                    tvCaloriesPercent.text =
                        getString(R.string.percent_value, recipe.caloriesDaily)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.nutrients.collectLatest { nutrients ->
                    val protein = nutrients.findLast { it.label == tvProteinTitle.text }!!
                    val carb = nutrients.findLast { it.label == tvCarbTitle.text }!!
                    val fat = nutrients.findLast { it.label == tvFatTitle.text }!!

                    tvProteinValue.text = protein.totalValue
                    tvCarbValue.text = carb.totalValue
                    tvFatValue.text = fat.totalValue

                    pbProtein.progress = protein.dailyValue
                    pbCarb.progress = carb.dailyValue
                    pbFat.progress = fat.dailyValue
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.labels.collectLatest { labels ->
                    llCategories.removeAllViews()
                    with(LayoutInflater.from(context)) {
                        for (category in labels.content.entries) {
                            llCategories.addView(createCategory(category, this))
                        }
                    }
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