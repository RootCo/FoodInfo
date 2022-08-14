package com.example.foodinfo.ui

import androidx.fragment.app.viewModels
import com.example.foodinfo.R
import com.example.foodinfo.databinding.FragmentBookmarkBinding
import com.example.foodinfo.utils.appComponent
import com.example.foodinfo.view_model.BookMarkViewModel


class BookMarkFragment : BaseFragment<FragmentBookmarkBinding>(
    FragmentBookmarkBinding::inflate
) {

    private val viewModel: BookMarkViewModel by viewModels {
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