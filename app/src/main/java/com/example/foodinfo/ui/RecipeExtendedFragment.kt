package com.example.foodinfo.ui

import androidx.core.view.get
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.foodinfo.R
import com.example.foodinfo.databinding.FragmentRecipeExtendedBinding
import com.example.foodinfo.repository.model.RecipeIngredientModel
import com.example.foodinfo.repository.model.RecipeModel
import com.example.foodinfo.repository.model.RecipeNutrientModel
import com.example.foodinfo.ui.adapter.RecipeCategoriesAdapter
import com.example.foodinfo.ui.custom_view.NonScrollLinearLayoutManager
import com.example.foodinfo.utils.*
import com.example.foodinfo.utils.glide.GlideApp
import com.example.foodinfo.view_model.RecipeExtendedViewModel
import com.google.android.material.imageview.ShapeableImageView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class RecipeExtendedFragment : BaseFragment<FragmentRecipeExtendedBinding>(
    FragmentRecipeExtendedBinding::inflate
) {

    private val args: RecipeExtendedFragmentArgs by navArgs()
    private var isInitialized = false

    private val viewModel: RecipeExtendedViewModel by viewModels {
        requireActivity().appComponent.viewModelsFactory()
    }

    private lateinit var recyclerAdapter: RecipeCategoriesAdapter

    private val onBackClickListener: () -> Unit = {
        findNavController().navigateUp()
    }

    private val onFavoriteClickListener: () -> Unit = {
        viewModel.updateFavoriteMark()
    }

    private val onShareClickListener: () -> Unit = { }

    private val onLabelClickListener: (String, String) -> Unit = { category, label ->
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            val labelItem = viewModel.getLabel(category, label)
            withContext(Dispatchers.Main) {
                showDescriptionDialog(
                    labelItem.label,
                    labelItem.description,
                    labelItem.preview
                )
            }
        }
    }

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
        recyclerAdapter = RecipeCategoriesAdapter(
            requireContext(),
            onLabelClickListener
        )

        isInitialized = false
        viewModel.recipeId = args.recipeId
        binding.btnBack.setOnClickListener { onBackClickListener() }
        binding.btnShare.setOnClickListener { onShareClickListener() }
        binding.btnFavorite.setOnClickListener { onFavoriteClickListener() }
        binding.tvNutrientsViewAll.setOnClickListener { onNutrientsViewAllClickListener() }
        binding.tvIngredientsViewAll.setOnClickListener { onIngredientsViewAllClickListener() }

        with(binding.llCategories) {
            layoutManager = NonScrollLinearLayoutManager(context).also {
                it.orientation = LinearLayoutManager.VERTICAL
                it.isScrollEnabled = false
            }
            adapter = recyclerAdapter
        }
    }

    override fun subscribeUI() {
        repeatOn(Lifecycle.State.STARTED) {
            if (!isInitialized) {
                binding.pbContent.isVisible = true
                binding.svContent.isVisible = false
            }

            combine(
                viewModel.recipe,
                viewModel.labels,
                viewModel.nutrients,
                viewModel.ingredients,
            ) { recipe, labels, nutrients, ingredients ->

                initRecipe(recipe)
                recyclerAdapter.submitList(labels)
                initNutrients(nutrients)
                initIngredients(ingredients)

                if (!isInitialized) {
                    binding.pbContent.isVisible = false
                    binding.svContent.isVisible = true
                    binding.svContent.baseAnimation()
                    isInitialized = true
                }

            }.collectLatest { }
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

        binding.btnFavorite.setFavorite(
            recipe.isFavorite,
            falseColor = R.attr.appMainFontColor
        )
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
}