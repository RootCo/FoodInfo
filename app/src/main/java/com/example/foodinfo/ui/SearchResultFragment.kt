package com.example.foodinfo.ui

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.foodinfo.R
import com.example.foodinfo.databinding.FragmentSearchResultBinding
import com.example.foodinfo.utils.appComponent
import com.example.foodinfo.view_model.SearchResultViewModel

class SearchResultFragment : BaseFragment<FragmentSearchResultBinding>(
    FragmentSearchResultBinding::inflate
) {

    private val args: SearchResultFragmentArgs by navArgs()

    private val viewModel: SearchResultViewModel by viewModels {
        requireActivity().appComponent.viewModelsFactory()
    }

    // возвращаемся на предыдущий экран минуя экран с вводом поиска
    private val onBackClickListener: () -> Unit = {
        findNavController().popBackStack(R.id.f_search_input, true)
    }

    private val onSearchClickListener: () -> Unit = {
        findNavController().navigate(
            SearchResultFragmentDirections.actionFSearchResultToFSearchInput()
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