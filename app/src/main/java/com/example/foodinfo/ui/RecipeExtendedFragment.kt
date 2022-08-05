package com.example.foodinfo.ui

import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.foodinfo.R
import com.example.foodinfo.databinding.FragmentRecipeExtendedBinding
import com.example.foodinfo.utils.applicationComponent
import com.example.foodinfo.view_model.RecipeExtendedViewModel

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


    override fun initUI() {
        val buttonBack: ImageView

        with(binding.root) {
            buttonBack = findViewById(R.id.btn_back)
        }

        buttonBack.setOnClickListener { onBackClickListener() }
    }

    override fun releaseUI() {

    }
}