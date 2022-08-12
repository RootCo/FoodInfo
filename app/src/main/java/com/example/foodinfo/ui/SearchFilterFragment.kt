package com.example.foodinfo.ui

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.foodinfo.databinding.FragmentSearchFilterBinding
import com.example.foodinfo.utils.applicationComponent
import com.example.foodinfo.view_model.SearchFilterViewModel

class SearchFilterFragment : BaseFragment<FragmentSearchFilterBinding>(
    FragmentSearchFilterBinding::inflate
) {

    private val viewModel: SearchFilterViewModel by viewModels {
        requireActivity().applicationComponent.viewModelsFactory()
    }

    private val onBackClickListener: () -> Unit = {
        findNavController().navigateUp()
    }


    override fun initUI(): Unit = with(binding) {
        btnBack.setOnClickListener { onBackClickListener() }
    }
}