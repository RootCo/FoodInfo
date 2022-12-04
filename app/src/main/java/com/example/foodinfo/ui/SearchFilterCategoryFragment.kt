package com.example.foodinfo.ui

import androidx.fragment.app.viewModels
import com.example.foodinfo.R
import com.example.foodinfo.databinding.FragmentSearchFilterCategoryBinding
import com.example.foodinfo.utils.appComponent
import com.example.foodinfo.view_model.SearchFilterCategoryViewModel


class SearchFilterCategoryFragment : BaseFragment<FragmentSearchFilterCategoryBinding>(
    FragmentSearchFilterCategoryBinding::inflate
) {

    private val viewModel: SearchFilterCategoryViewModel by viewModels {
        requireActivity().appComponent.viewModelsFactory()
    }


    override fun initUI() {
        super.initUI()
        binding.hint.textView.text = getString(
            R.string.TBD_screen,
            viewModel.featureName
        )
    }
}