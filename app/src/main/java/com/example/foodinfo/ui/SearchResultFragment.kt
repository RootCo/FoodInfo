package com.example.foodinfo.ui

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.foodinfo.R
import com.example.foodinfo.databinding.FragmentSearchResultBinding
import com.example.foodinfo.utils.applicationComponent
import com.example.foodinfo.view_model.SearchResultViewModel

class SearchResultFragment : BaseFragment<FragmentSearchResultBinding>(
    FragmentSearchResultBinding::inflate
) {

    private val args: SearchResultFragmentArgs by navArgs()

    private val viewModel: SearchResultViewModel by viewModels {
        activity!!.applicationComponent.viewModelsFactory()
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


    override fun initUI(): Unit = with(binding) {
        tvRecipeName.text = args.inputText
        btnBack.setOnClickListener { onBackClickListener() }
        btnSearch.setOnClickListener { onSearchClickListener() }
    }
}