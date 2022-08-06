package com.example.foodinfo.ui

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.foodinfo.databinding.FragmentSearchTargetBinding
import com.example.foodinfo.utils.applicationComponent
import com.example.foodinfo.view_model.SearchTargetViewModel

class SearchTargetFragment : BaseFragment<FragmentSearchTargetBinding>(
    FragmentSearchTargetBinding::inflate
) {

    private val args: SearchTargetFragmentArgs by navArgs()

    private val viewModel: SearchTargetViewModel by viewModels {
        activity!!.applicationComponent.viewModelsFactory()
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
        tvHeader.text = args.label
        btnBack.setOnClickListener { onBackClickListener() }
        btnSearch.setOnClickListener { onSearchClickListener() }
    }
}