package com.example.foodinfo.ui

import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.foodinfo.R
import com.example.foodinfo.databinding.FragmentSearchResultBinding
import com.example.foodinfo.utils.applicationComponent
import com.example.foodinfo.view_model.SearchResultViewModel

class SearchResultFragment : BaseDataFragment<FragmentSearchResultBinding>(
    FragmentSearchResultBinding::inflate
) {

    private val args: SearchResultFragmentArgs by navArgs()


    private val viewModel: SearchResultViewModel by viewModels {
        activity!!.applicationComponent.viewModelsFactory()
    }

    override fun updateViewModelData() {
        viewModel.updateRecipes(args.inputText)
    }

    override fun initUI() {
        binding.root.findViewById<TextView>(R.id.tv_header).text = args.inputText

        // возвращаемся на предыдущий экран минуя экран с вводом поиска
        binding.root.findViewById<ImageView>(R.id.btn_back).setOnClickListener {
            findNavController().popBackStack(R.id.f_search_input, true)
        }

        binding.root.findViewById<ImageView>(R.id.btn_search).setOnClickListener {
            findNavController().navigate(
                SearchResultFragmentDirections.actionFSearchResultToFSearchInput()
            )
        }
    }

}