package com.example.foodinfo.ui

import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodinfo.R
import com.example.foodinfo.databinding.FragmentHomeBinding
import com.example.foodinfo.model.local.RecipeExplore
import com.example.foodinfo.ui.adapter.HomeRecipesAdapter
import com.example.foodinfo.ui.decorator.HomeItemDecoration
import com.example.foodinfo.utils.Utils
import com.example.foodinfo.utils.applicationComponent
import com.example.foodinfo.utils.handleNoData
import com.example.foodinfo.view_model.HomeViewModel


class HomeFragment : BaseDataFragment<FragmentHomeBinding>(
    FragmentHomeBinding::inflate
) {

    private val viewModel: HomeViewModel by viewModels {
        activity!!.applicationComponent.viewModelsFactory()
    }

    override fun updateViewModelData() {
        viewModel.updateRecipes()
        viewModel.updateFood()
    }

    override fun initUI() {
        val recipesRecycler =
            binding.root.findViewById<RecyclerView>(R.id.rv_home_recipes)

        val recipesAdapter = HomeRecipesAdapter(
            binding.root.context, Utils(binding.root.context), onItemClickListener
        )

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recipesRecycler)

        val layoutManager = LinearLayoutManager(binding.root.context)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recipesRecycler.layoutManager = layoutManager
        recipesRecycler.setHasFixedSize(true)
        recipesRecycler.addItemDecoration(
            HomeItemDecoration(
                resources.getDimensionPixelSize(R.dimen.home_recipes_space),
                resources.getDimensionPixelSize(R.dimen.home_recipes_margin)
            )
        )
        recipesRecycler.adapter = recipesAdapter

        viewModel.recipes.observe(viewLifecycleOwner) { recipes ->
            when (recipes != null) {
                true  -> recipesAdapter.submitList(recipes)
                false -> handleNoData()
            }
        }

        viewModel.dailyRecipe.observe(viewLifecycleOwner) { food ->
            when (food != null) {
                true  -> setDailyRecipe(food)
                false -> handleNoData()
            }
        }
    }


    private val onItemClickListener: (String) -> Unit = { id ->
        findNavController().navigate(
            HomeFragmentDirections.actionFHomeToFRecipeExtended(id)
        )
    }


    private fun setDailyRecipe(recipe: RecipeExplore) {
        Glide.with(this)
            .load(recipe.preview)
            .into(binding.root.findViewById(R.id.iv_daily_recipe_preview))
        binding.root.findViewById<TextView>(R.id.tv_daily_recipe_calories).text =
            recipe.calories.toString()
        binding.root.findViewById<TextView>(R.id.tv_daily_recipe_name).text =
            recipe.name
    }
}