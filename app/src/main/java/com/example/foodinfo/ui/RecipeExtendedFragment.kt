package com.example.foodinfo.ui

import androidx.core.view.get
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.foodinfo.R
import com.example.foodinfo.databinding.FragmentRecipeExtendedBinding
import com.example.foodinfo.repository.model.RecipeExtendedModel
import com.example.foodinfo.ui.adapter.RecipeCategoriesAdapter
import com.example.foodinfo.ui.custom_view.NonScrollLinearLayoutManager
import com.example.foodinfo.ui.decorator.ListItemDecoration
import com.example.foodinfo.utils.*
import com.example.foodinfo.utils.glide.GlideApp
import com.example.foodinfo.view_model.RecipeExtendedViewModel
import com.google.android.material.imageview.ShapeableImageView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class RecipeExtendedFragment : BaseFragment<FragmentRecipeExtendedBinding>(
    FragmentRecipeExtendedBinding::inflate
) {

    private val args: RecipeExtendedFragmentArgs by navArgs()

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
                    labelItem.name,
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

        viewModel.recipeId = args.recipeId
        binding.btnBack.setOnClickListener { onBackClickListener() }
        binding.btnShare.setOnClickListener { onShareClickListener() }
        binding.btnFavorite.setOnClickListener { onFavoriteClickListener() }
        binding.tvNutrientsViewAll.setOnClickListener { onNutrientsViewAllClickListener() }
        binding.tvIngredientsViewAll.setOnClickListener { onIngredientsViewAllClickListener() }

        with(binding.llCategories) {
            layoutManager = NonScrollLinearLayoutManager(context).also {
                it.orientation = LinearLayoutManager.VERTICAL
            }
            addItemDecoration(
                ListItemDecoration(
                    resources.getDimensionPixelSize(R.dimen.recipe_extended_category_item_space),
                    RecyclerView.VERTICAL
                )
            )
            adapter = recyclerAdapter
        }
    }

    override fun subscribeUI() {
        observeUiState { uiState ->
            when (uiState) {
                is UiState.Error   -> {}
                is UiState.Success -> {
                    binding.pbContent.isVisible = false
                    binding.svContent.isVisible = true
                    binding.svContent.baseAnimation()
                }
                is UiState.Loading -> {
                    binding.pbContent.isVisible = true
                    binding.svContent.isVisible = false
                }
            }

        }

        repeatOn(Lifecycle.State.STARTED) {
            viewModel.recipe.collectLatest { recipe ->
                val state: UiState = when (recipe) {
                    is State.Success -> {
                        initRecipe(recipe.data)
                        UiState.Success()
                    }
                    is State.Error   -> {
                        UiState.Error(recipe.message, recipe.error)
                    }
                    else             -> {
                        UiState.Loading()
                    }
                }
                updateUiState(state)
            }
        }
    }


    private fun initRecipe(recipe: RecipeExtendedModel) {
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

        recipe.protein.apply {
            binding.iProtein.tvTitle.text = label
            binding.iProtein.tvValue.text = getString(R.string.float_value, totalWeight)
            binding.iProtein.progressBar.progress = dailyPercent
        }

        recipe.carb.apply {
            binding.iCarbs.tvTitle.text = label
            binding.iCarbs.tvValue.text = getString(R.string.float_value, totalWeight)
            binding.iCarbs.progressBar.progress = dailyPercent
        }

        recipe.fat.apply {
            binding.iFat.tvTitle.text = label
            binding.iFat.tvValue.text = getString(R.string.float_value, totalWeight)
            binding.iFat.progressBar.progress = dailyPercent
        }

        for (index in 0..3) {
            GlideApp.with(requireContext())
                .load(recipe.ingredients.getOrNull(index)?.previewURL)
                .into(binding.clIngredients[index] as ShapeableImageView)
        }

        recyclerAdapter.submitList(recipe.categories)
    }
}