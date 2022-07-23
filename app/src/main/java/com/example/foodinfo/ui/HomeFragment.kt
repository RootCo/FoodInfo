package com.example.foodinfo.ui

import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodinfo.R
import com.example.foodinfo.databinding.FragmentHomeBinding
import com.example.foodinfo.model.local.RecipeExplore
import com.example.foodinfo.ui.adapter.HomeRecipesAdapter
import com.example.foodinfo.ui.decorator.HomeItemDecoration
import com.example.foodinfo.utils.applicationComponent
import com.example.foodinfo.view_model.HomeViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class HomeFragment : BaseDataFragment<FragmentHomeBinding>(
    FragmentHomeBinding::inflate
) {

    private val viewModel: HomeViewModel by viewModels {
        activity!!.applicationComponent.viewModelsFactory()
    }

    private var dailyRecipeJob: Job? = null
    private var popularRecipesJob: Job? = null

    override fun updateViewModelData() {
    }

    override fun initUI() {
        val recipesProgress: ProgressBar
        val recipesRecycler: RecyclerView
        val recipesAdapter: HomeRecipesAdapter
        val layoutManager: LinearLayoutManager

        with(binding.root) {
            layoutManager = LinearLayoutManager(context)
            recipesProgress = findViewById(R.id.home_progress)
            recipesRecycler = findViewById(R.id.rv_home_recipes)
            recipesAdapter = HomeRecipesAdapter(context, onItemClickListener)
        }

        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recipesAdapter.addLoadStateListener { state: CombinedLoadStates ->
            recipesRecycler.isVisible = state.refresh != LoadState.Loading
            recipesProgress.isVisible = state.refresh == LoadState.Loading
        }

        with(recipesRecycler)
        {
            this.layoutManager = layoutManager
            setHasFixedSize(true)
            addItemDecoration(
                HomeItemDecoration(
                    resources.getDimensionPixelSize(R.dimen.home_recipes_space),
                    resources.getDimensionPixelSize(R.dimen.home_recipes_margin)
                )
            )
            adapter = recipesAdapter
        }

        dailyRecipeJob?.cancel()
        dailyRecipeJob = lifecycleScope.launch {
            viewModel.dailyRecipe.collectLatest(::setDailyRecipe)
        }

        popularRecipesJob?.cancel()
        popularRecipesJob = lifecycleScope.launch {
            viewModel.recipes.collectLatest(recipesAdapter::submitData)
        }
    }


    private val onItemClickListener: (String) -> Unit = { id ->
        findNavController().navigate(
            HomeFragmentDirections.actionFHomeToFRecipeExtended(id)
        )
    }


    private fun setDailyRecipe(recipe: RecipeExplore?) {
        recipe ?: return
        with(binding.root) {
            Glide.with(this)
                .load(recipe.preview)
                .into(findViewById(R.id.iv_daily_recipe_preview))
            findViewById<TextView>(R.id.tv_daily_recipe_calories).text =
                recipe.calories.toString()
            findViewById<TextView>(R.id.tv_daily_recipe_name).text = recipe.name
            findViewById<FrameLayout>(R.id.lout_daily_recipe).setOnClickListener {
                findNavController().navigate(
                    HomeFragmentDirections.actionFHomeToFRecipeExtended(recipe.id)
                )
            }
        }
    }
}