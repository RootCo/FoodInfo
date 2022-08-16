package com.example.foodinfo.ui

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.foodinfo.R
import com.example.foodinfo.databinding.FragmentSearchTargetBinding
import com.example.foodinfo.utils.appComponent
import com.example.foodinfo.utils.showDescriptionDialog
import com.example.foodinfo.view_model.SearchTargetViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

    private val onLabelClickListener: () -> Unit = {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            val labelItem = viewModel.getLabel(args.category, args.label)
            withContext(Dispatchers.Main) {
                showDescriptionDialog(
                    labelItem.label,
                    labelItem.description,
                    labelItem.preview
                )
            }
        }
    }


    override fun initUI(): Unit = with(binding) {
        tvLabel.text = args.label
        tvLabel.setOnClickListener { onLabelClickListener() }
        btnBack.setOnClickListener { onBackClickListener() }
        btnSearch.setOnClickListener { onSearchClickListener() }

        binding.hint.textView.text = getString(
            R.string.TBD_screen,
            viewModel.featureName
        )
    }
}