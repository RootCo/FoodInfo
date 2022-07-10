package com.example.foodinfo.ui

import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.foodinfo.R
import com.example.foodinfo.databinding.FragmentRecipeExtendedBinding
import com.example.foodinfo.utils.applicationComponent
import com.example.foodinfo.utils.handleNoData
import com.example.foodinfo.view_model.RecipeExtendedViewModel

class RecipeExtendedFragment : BaseDataFragment<FragmentRecipeExtendedBinding>(
    FragmentRecipeExtendedBinding::inflate
) {

    private val args: RecipeExtendedFragmentArgs by navArgs()


    private val viewModel: RecipeExtendedViewModel by viewModels {
        activity!!.applicationComponent.viewModelsFactory()
    }

    override fun updateViewModelData() {
        viewModel.loadRecipe(args.recipeId)
    }

    override fun initUI() {
        binding.root.findViewById<ImageView>(R.id.btn_back).setOnClickListener {
            findNavController().navigateUp()
        }

        viewModel.recipe.observe(viewLifecycleOwner) { recipe ->
            when (recipe != null) {
                true  -> {
                    binding.root.findViewById<TextView>(R.id.tv_header).text = recipe.name
                }
                false -> handleNoData()
            }
        }
    }
}