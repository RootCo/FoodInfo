package com.example.foodinfo.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.get
import androidx.core.view.isVisible
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
import com.example.foodinfo.databinding.ItemExtendedCategoryBinding
import com.example.foodinfo.repository.model.RecipeIngredientModel
import com.example.foodinfo.repository.model.RecipeLabelsModel
import com.example.foodinfo.repository.model.RecipeModel
import com.example.foodinfo.repository.model.RecipeNutrientModel
import com.example.foodinfo.utils.appComponent
import com.example.foodinfo.utils.glide.GlideApp
import com.example.foodinfo.view_model.RecipeExtendedViewModel
import com.google.android.material.chip.Chip
import com.google.android.material.imageview.ShapeableImageView
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis


class RecipeExtendedFragment : BaseFragment<FragmentRecipeExtendedBinding>(
    FragmentRecipeExtendedBinding::inflate
) {

    private val args: RecipeExtendedFragmentArgs by navArgs()

    private val viewModel: RecipeExtendedViewModel by viewModels {
        requireActivity().appComponent.viewModelsFactory()
    }

    private val onBackClickListener: () -> Unit = {
        findNavController().navigateUp()
    }

    private val onLikeClickListener: () -> Unit = { }

    private val onShareClickListener: () -> Unit = { }

    private val onLabelClickListener: (label: String) -> Unit = { }

    private val onNutrientsViewAllClickListener: () -> Unit = { }

    private val onIngredientsViewAllClickListener: () -> Unit = {
        findNavController().navigate(
            RecipeExtendedFragmentDirections.actionFRecipeExtendedToFRecipeIngredients(
                args.recipeId
            )
        )
    }


    override fun initUI() {
        viewModel.recipeId = args.recipeId
        binding.btnBack.setOnClickListener { onBackClickListener() }
        binding.btnLike.setOnClickListener { onLikeClickListener() }
        binding.btnShare.setOnClickListener { onShareClickListener() }
        binding.tvNutrientsViewAll.setOnClickListener { onNutrientsViewAllClickListener() }
        binding.tvIngredientsViewAll.setOnClickListener { onIngredientsViewAllClickListener() }
    }

    override fun subscribeUI() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {

                binding.pbContent.isVisible = true
                binding.svContent.isVisible = false

                combine(
                    viewModel.recipe,
                    viewModel.labels,
                    viewModel.nutrients,
                    viewModel.ingredients,
                ) { recipe, labels, nutrients, ingredients ->

                    Log.d("tag", "recipe ${measureTimeMillis { initRecipe(recipe) }}")
                    Log.d("tag", "labels ${measureTimeMillis { initLabels(labels) }}")
                    Log.d(
                        "tag",
                        "nutrients ${measureTimeMillis { initNutrients(nutrients) }}"
                    )
                    Log.d(
                        "tag",
                        "ingredients ${measureTimeMillis { initIngredients(ingredients) }}"
                    )

                    //                    initRecipe(recipe)
                    //                    initLabels(labels)
                    //                    initNutrients(nutrients)
                    //                    initIngredients(ingredients)

                    binding.pbContent.isVisible = false
                    binding.svContent.isVisible = true
                    binding.svContent.apply {
                        alpha = 0f
                        animate().alpha(1f).setDuration(100).setListener(null)
                    }

                }.collectLatest { }
            }
        }
    }


    private fun initRecipe(recipe: RecipeModel) {
        binding.tvRecipeName.text = recipe.name
        Glide.with(binding.ivRecipePreview.context)
            .load(recipe.previewURL)
            .error(R.drawable.ic_no_image)
            .placeholder(null)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(binding.ivRecipePreview)

        binding.tvServingsValue.text = recipe.servings
        binding.tvWeightValue.text = recipe.totalWeight
        binding.tvTimeValue.text = recipe.totalTime

        binding.tvCaloriesValue.text = recipe.calories
        binding.pbCalories.progress = recipe.caloriesDaily
        binding.tvCaloriesPercent.text = getString(
            R.string.percent_value,
            recipe.caloriesDaily
        )
    }

    private fun initLabels(labels: RecipeLabelsModel) {
        binding.llCategories.removeAllViews()
        with(LayoutInflater.from(context)) {
            for (category in labels.content.entries) {
                binding.llCategories.addView(createCategory(category, this))
            }
        }
    }

    private fun initNutrients(nutrients: List<RecipeNutrientModel>) {
        val protein = nutrients.findLast { nutrient ->
            nutrient.label == binding.tvProteinTitle.text
        }!!
        val carb = nutrients.findLast { nutrient ->
            nutrient.label == binding.tvCarbTitle.text
        }!!
        val fat = nutrients.findLast { nutrient ->
            nutrient.label == binding.tvFatTitle.text
        }!!

        binding.tvProteinValue.text = protein.totalValue
        binding.tvCarbValue.text = carb.totalValue
        binding.tvFatValue.text = fat.totalValue

        binding.pbProtein.progress = protein.dailyValue
        binding.pbCarb.progress = carb.dailyValue
        binding.pbFat.progress = fat.dailyValue
    }

    private fun initIngredients(ingredients: List<RecipeIngredientModel>) {
        for (index in 0..4) {
            GlideApp.with(requireContext())
                .load(ingredients.getOrNull(index)?.previewURL)
                .into(binding.clIngredients[index] as ShapeableImageView)
        }
    }


    private fun createCategory(
        category: Map.Entry<String, List<String>>,
        inflater: LayoutInflater
    ): View {
        val categoryView = ItemExtendedCategoryBinding.inflate(inflater, null, false)
        categoryView.tvTitle.text = category.key
        for (label in category.value) {
            categoryView.cgLabels.addView(createLabel(label))
        }
        return categoryView.root
    }

    private fun createLabel(label: String): Chip {
        return Chip(context, null, R.attr.appChipStyle).apply {
            text = label
            setOnClickListener { onLabelClickListener(label) }
        }
    }
}