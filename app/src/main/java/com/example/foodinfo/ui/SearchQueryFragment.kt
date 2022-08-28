package com.example.foodinfo.ui

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.foodinfo.R
import com.example.foodinfo.databinding.FragmentSearchQueryBinding
import com.example.foodinfo.utils.appComponent
import com.example.foodinfo.view_model.SearchQueryViewModel

class SearchQueryFragment : BaseFragment<FragmentSearchQueryBinding>(
    FragmentSearchQueryBinding::inflate
) {

    private val args: SearchQueryFragmentArgs by navArgs()

    private val viewModel: SearchQueryViewModel by viewModels {
        requireActivity().appComponent.viewModelsFactory()
    }

    // возвращаемся на предыдущий экран минуя экран с вводом поиска
    private val onBackClickListener: () -> Unit = {
        findNavController().popBackStack(R.id.f_search_input, true)
    }

    private val onSearchClickListener: () -> Unit = {
        findNavController().navigate(
            SearchQueryFragmentDirections.actionFSearchQueryToFSearchInput()
        )
    }


    override fun initUI() {
        binding.tvRecipeName.text = args.inputText
        binding.btnBack.setOnClickListener { onBackClickListener() }
        binding.btnSearch.setOnClickListener { onSearchClickListener() }

        binding.hint.textView.text = getString(
            R.string.TBD_screen,
            viewModel.featureName
        )
    }
}