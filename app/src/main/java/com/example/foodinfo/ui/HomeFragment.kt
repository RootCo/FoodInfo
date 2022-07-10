package com.example.foodinfo.ui

import android.widget.TableLayout
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodinfo.R
import com.example.foodinfo.databinding.FragmentHomeBinding
import com.example.foodinfo.model.entities.Food
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
                resources.getDimensionPixelSize(R.dimen.home_recipes_margin)
            )
        )
        recipesRecycler.adapter = recipesAdapter

        viewModel.recipes.observe(viewLifecycleOwner) { recipes ->
            when (recipes != null) {
                true  -> recipesAdapter.setRecipesList(recipes)
                false -> handleNoData()
            }
        }

        viewModel.food.observe(viewLifecycleOwner) { food ->
            when (food != null) {
                true  -> setIngredient(food)
                false -> handleNoData()
            }
        }
    }


    private val onItemClickListener: (String) -> Unit = { id ->
        findNavController().navigate(
            HomeFragmentDirections.actionFHomeToFRecipeExtended(id)
        )
    }


    private fun setIngredient(food: Food) {
        Glide.with(this)
            .load(food.preview)
            .into(binding.root.findViewById(R.id.iv_home_ingredient))

        binding.root.findViewById<TextView>(R.id.tv_home_ingredient_calories).text =
            food.calories.toString()

        val pfcTable = binding.root.findViewById<TableLayout>(R.id.tb_ingredient_pfc)
        pfcTable.findViewById<TextView>(R.id.tv_pfc_protein).text =
            food.protein.toString()
        pfcTable.findViewById<TextView>(R.id.tv_pfc_fat).text = food.fat.toString()
        pfcTable.findViewById<TextView>(R.id.tv_pfc_carb).text = food.carb.toString()
    }
}