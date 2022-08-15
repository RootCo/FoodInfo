package com.example.foodinfo.ui

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

    private val onNutrientsViewAllClickListener: () -> Unit = {
        findNavController().navigate(
            RecipeExtendedFragmentDirections.actionFRecipeExtendedToFRecipeNutrients(
                args.recipeId
            )
        )
    }

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

                    initRecipe(recipe)
                    initLabels(labels)
                    initNutrients(nutrients)
                    initIngredients(ingredients)

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

        binding.tvServingsValue.text = getString(
            R.string.serving_value,
            recipe.servings
        )
        binding.tvWeightValue.text = getString(
            R.string.gram_int_value,
            recipe.totalWeight
        )
        binding.tvTimeValue.text = getString(
            R.string.time_value,
            recipe.totalTime
        )

        binding.iEnergy.tvTitle.text = resources.getString(R.string.calories_header)
        binding.iEnergy.tvValue.text = recipe.calories
        binding.iEnergy.progressBar.progress = recipe.caloriesDaily
    }

    private fun initLabels(labels: RecipeLabelsModel) {
        binding.llCategories.removeAllViews()
        with(LayoutInflater.from(context)) {
            labels.content.forEach { (name, labels) ->
                binding.llCategories.addView(createCategory(name, labels, this))
            }
        }
    }

    private fun initNutrients(nutrients: List<RecipeNutrientModel>) {
        nutrients.findLast { nutrient ->
            nutrient.label == resources.getString(R.string.protein_header)
        }!!.apply {
            binding.iProtein.tvTitle.text = label
            binding.iProtein.tvValue.text = getString(R.string.float_value, totalWeight)
            binding.iProtein.progressBar.progress = dailyPercent
        }

        nutrients.findLast { nutrient ->
            nutrient.label == resources.getString(R.string.carbs_header)
        }!!.apply {
            binding.iCarbs.tvTitle.text = label
            binding.iCarbs.tvValue.text = getString(R.string.float_value, totalWeight)
            binding.iCarbs.progressBar.progress = dailyPercent
        }

        nutrients.findLast { nutrient ->
            nutrient.label == resources.getString(R.string.fat_header)
        }!!.apply {
            binding.iFat.tvTitle.text = label
            binding.iFat.tvValue.text = getString(R.string.float_value, totalWeight)
            binding.iFat.progressBar.progress = dailyPercent
        }
    }

    private fun initIngredients(ingredients: List<RecipeIngredientModel>) {
        for (index in 0..3) {
            GlideApp.with(requireContext())
                .load(ingredients.getOrNull(index)?.previewURL)
                .into(binding.clIngredients[index] as ShapeableImageView)
        }
    }


    private fun createCategory(
        name: String,
        labels: List<String>,
        inflater: LayoutInflater
    ): View {
        return ItemExtendedCategoryBinding.inflate(inflater, null, false).apply {
            tvTitle.text = name
            for (label in labels) {
                cgLabels.addView(createLabel(label))
            }
        }.root
    }

    private fun createLabel(label: String): Chip {
        return Chip(context, null, R.attr.appChipStyle).apply {
            text = label
            setOnClickListener { onLabelClickListener(label) }
        }
    }
}