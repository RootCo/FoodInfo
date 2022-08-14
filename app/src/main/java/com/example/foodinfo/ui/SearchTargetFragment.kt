package com.example.foodinfo.ui

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.foodinfo.R
import com.example.foodinfo.databinding.FragmentSearchTargetBinding
import com.example.foodinfo.utils.appComponent
import com.example.foodinfo.view_model.SearchTargetViewModel

class SearchTargetFragment : BaseFragment<FragmentSearchTargetBinding>(
    FragmentSearchTargetBinding::inflate
) {

    private val args: SearchTargetFragmentArgs by navArgs()

    private val viewModel: SearchTargetViewModel by viewModels {
        requireActivity().appComponent.viewModelsFactory()
    }

    private val onBackClickListener: () -> Unit = {
        findNavController().navigateUp()
    }

    private val onSearchClickListener: () -> Unit = {
        findNavController().navigate(
            SearchTargetFragmentDirections.actionFSearchTargetToFSearchInput()
        )
    }


    override fun initUI(): Unit = with(binding) {
        tvRecipeName.text = args.label
        btnBack.setOnClickListener { onBackClickListener() }
        btnSearch.setOnClickListener { onSearchClickListener() }

        binding.hint.textView.text = getString(
            R.string.TBD_screen,
            viewModel.featureName
        )
    }
}