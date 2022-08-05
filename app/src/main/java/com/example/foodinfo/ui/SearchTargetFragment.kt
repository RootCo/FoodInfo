package com.example.foodinfo.ui

import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.foodinfo.R
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
            SearchResultFragmentDirections.actionFSearchResultToFSearchInput()
        )
    }


    override fun initUI() {
        val textViewHeader: TextView
        val buttonSearch: ImageView
        val buttonBack: ImageView

        with(binding.root) {
            textViewHeader = findViewById(R.id.tv_header)
            buttonSearch = findViewById(R.id.btn_search)
            buttonBack = findViewById(R.id.btn_back)
        }

        textViewHeader.text = args.label

        buttonSearch.setOnClickListener { onSearchClickListener() }
        buttonBack.setOnClickListener { onBackClickListener() }
    }

    override fun releaseUI() {

    }
}