package com.example.foodinfo.ui

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.foodinfo.databinding.FragmentSearchFilterBinding
import com.example.foodinfo.utils.appComponent
import com.example.foodinfo.view_model.SearchFilterViewModel


class SearchFilterFragment : BaseFragment<FragmentSearchFilterBinding>(
    FragmentSearchFilterBinding::inflate
) {

    private val viewModel: SearchFilterViewModel by viewModels {
        requireActivity().appComponent.viewModelsFactory()
    }

    private val onBackClickListener: () -> Unit = {
        findNavController().navigateUp()
    }


    override fun initUI() {
        binding.btnBack.setOnClickListener { onBackClickListener() }
    }
}