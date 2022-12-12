package com.example.foodinfo.ui

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.foodinfo.R
import com.example.foodinfo.databinding.FragmentFavoriteSortBinding
import com.example.foodinfo.utils.appComponent
import com.example.foodinfo.view_model.FavoriteSortViewModel


class FavoriteSortFragment : BaseFragment<FragmentFavoriteSortBinding>(
    FragmentFavoriteSortBinding::inflate
) {

    private val viewModel: FavoriteSortViewModel by viewModels {
        requireActivity().appComponent.viewModelsFactory()
    }

    private val onBackClickListener: () -> Unit = {
        findNavController().navigateUp()
    }


    override fun initUI() {
        binding.btnBack.setOnClickListener { onBackClickListener() }

        binding.hint.textView.text = getString(
            R.string.TBD_screen,
            viewModel.featureName
        )
    }
}